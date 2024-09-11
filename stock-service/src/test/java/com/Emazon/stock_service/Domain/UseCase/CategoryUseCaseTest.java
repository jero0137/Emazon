package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.InvalidPageSizeException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Category category = new Category(1L, "Valid Name", "");
        assertThrows(MissingAttributeException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        Category category = new Category(1L,"", "Valid Description");
        assertThrows(MissingAttributeException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        String longName = "A".repeat(51); // assuming 51 characters exceeds the limit
        Category category = new Category(1L ,longName, "Valid Description");
        assertThrows(InvalidLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        String longDescription = "A".repeat(91); // assuming 91 characters exceeds the limit
        Category category = new Category(1L, "Valid Name", longDescription);
        assertThrows(InvalidLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldSaveCategorySuccessfullyWhenValid() {

        Category newCategory = new Category(null, "Computadores", "Todo lo relacionado a computadores");

        Mockito.doNothing().when(categoryPersistencePort).saveCategory(newCategory);

        categoryUseCase.saveCategory(newCategory);

        Mockito.verify(categoryPersistencePort).saveCategory(newCategory);
    }

    @Test
    void shouldGetCategorySuccessfullyWhenValid() {
        Category category = new Category(1L,"Computadores","Todo lo relacionado a computadores");
        Mockito.when(categoryPersistencePort.getCategory(1L)).thenReturn(category);
        Category result = categoryUseCase.getCategory(1L);
        assertEquals(category, result);
    }

    @Test
    void shouldReturnCategoriesInAscendingOrder() {
        int page = 0;
        int size = 10;
        String sort = "name";
        Sort.Direction direction = Sort.Direction.ASC;

        Pagination pagination = new Pagination(page, size, sort, direction);
        List<Category> categories = List.of(
                new Category(1L, "Books", "All kinds of books"),
                new Category(2L, "Computers", "All about computers"),
                new Category(3L, "Electronics", "Various electronic items")
        );
        PageCustom<Category> expectedPage = new PageCustom<>(categories, 0, 10, 3, 1);
        Mockito.when(categoryPersistencePort.getCategories(pagination)).thenReturn(expectedPage);

        PageCustom<Category> result = categoryUseCase.getCategories(pagination);
        assertEquals(expectedPage, result);
        assertEquals("Books", result.getContent().get(0).getName());
        assertEquals("Computers", result.getContent().get(1).getName());
        assertEquals("Electronics", result.getContent().get(2).getName());
    }

    @Test
    void shouldReturnCategoriesInDescendingOrder() {
        int page = 0;
        int size = 10;
        String sort = "name";
        Sort.Direction direction = Sort.Direction.DESC;

        Pagination pagination = new Pagination(page, size, sort, direction);
        List<Category> categories = List.of(
                new Category(3L, "Electronics", "Various electronic items"),
                new Category(2L, "Computers", "All about computers"),
                new Category(1L, "Books", "All kinds of books")
        );
        PageCustom<Category> expectedPage = new PageCustom<>(categories, 0, 10, 3, 1);
        Mockito.when(categoryPersistencePort.getCategories(pagination)).thenReturn(expectedPage);

        PageCustom<Category> result = categoryUseCase.getCategories(pagination);
        assertEquals(expectedPage, result);
        assertEquals("Electronics", result.getContent().get(0).getName());
        assertEquals("Computers", result.getContent().get(1).getName());
        assertEquals("Books", result.getContent().get(2).getName());
    }

    @Test
    void shouldReturnEmptyPageCustomWhenNoCategories() {
        int page = 0;
        int size = 10;
        String sort = "name";
        Sort.Direction direction = Sort.Direction.ASC;

        Pagination pagination = new Pagination(page, size, sort, direction);
        PageCustom<Category> expectedPage = new PageCustom<>(List.of(), 0, 10, 0, 0);
        Mockito.when(categoryPersistencePort.getCategories(pagination)).thenReturn(expectedPage);
        PageCustom<Category> result = categoryUseCase.getCategories(pagination);
        assertEquals(expectedPage, result);
    }

    @Test
    void shouldThrowExceptionWhenPageOutOfBounds() {
        Pagination pagination = new Pagination(10, 10, "name", Sort.Direction.ASC);
        Mockito.when(categoryPersistencePort.getCategories(pagination)).thenThrow(new PageOutOfBoundsException());
        assertThrows(PageOutOfBoundsException.class, () -> categoryUseCase.getCategories(pagination));
    }

    @Test
    void shouldThrowExceptionWhenCategoryIdIsNull() {
        assertThrows(MissingAttributeException.class, () -> categoryUseCase.getCategory(null));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {
        Long nonExistentId = 999L;
        Mockito.when(categoryPersistencePort.getCategory(nonExistentId)).thenReturn(null);
        assertThrows(CategoryNotFoundException.class, () -> categoryUseCase.getCategory(nonExistentId));
    }

    @Test
    void shouldReturnCategoryWhenIdIsValid() {
        Category category = new Category(1L, "Computadores", "Todo lo relacionado a computadores");
        Mockito.when(categoryPersistencePort.getCategory(1L)).thenReturn(category);
        Category result = categoryUseCase.getCategory(1L);
        assertEquals(category, result);
    }

    @Test
    void shouldReturnAllCategoriesSuccessfully() {
        List<Category> categories = List.of(
                new Category(1L, "Books", "All kinds of books"),
                new Category(2L, "Computers", "All about computers")
        );
        Mockito.when(categoryPersistencePort.getAllCategories()).thenReturn(categories);
        List<Category> result = categoryUseCase.getAllCategories();
        assertEquals(categories, result);
    }

    @Test
    void shouldUpdateCategorySuccessfully() {
        Category category = new Category(1L, "Books", "All kinds of books");
        Mockito.doNothing().when(categoryPersistencePort).updateCategory(category);
        categoryUseCase.updateCategory(category);
        Mockito.verify(categoryPersistencePort).updateCategory(category);
    }

    @Test
    void shouldDeleteCategorySuccessfully() {
        Long categoryId = 1L;
        Mockito.doNothing().when(categoryPersistencePort).deleteCategoryById(categoryId);
        categoryUseCase.deleteCategory(categoryId);
        Mockito.verify(categoryPersistencePort).deleteCategoryById(categoryId);
    }

    @Test
    void shouldThrowExceptionWhenPageIsNegative() {
        Pagination pagination = new Pagination(-1, 10, "name", Sort.Direction.ASC);
        assertThrows(PageOutOfBoundsException.class, () -> categoryUseCase.getCategories(pagination));
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsZero() {
        Pagination pagination = new Pagination(0, 0, "name", Sort.Direction.ASC);
        assertThrows(InvalidPageSizeException.class, () -> categoryUseCase.getCategories(pagination));
    }
}