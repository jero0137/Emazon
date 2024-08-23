package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
    public void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Category category = new Category(1L, "Valid Name", "");
        assertThrows(MissingAttributeException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        Category category = new Category(1L,"", "Valid Description");
        assertThrows(MissingAttributeException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenNameExceedsMaxLength() {
        String longName = "A".repeat(51); // assuming 51 characters exceeds the limit
        Category category = new Category(1L ,longName, "Valid Description");
        assertThrows(InvalidLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        String longDescription = "A".repeat(91); // assuming 91 characters exceeds the limit
        Category category = new Category(1L, "Valid Name", longDescription);
        assertThrows(InvalidLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldSaveCategorySuccessfullyWhenValid() {
        Category newCategory = new Category(null,"Computadores","Todo lo relacionado a computadores");
        Mockito.doNothing().when(categoryPersistencePort).saveCategory(newCategory);
        categoryUseCase.saveCategory(newCategory);
        Mockito.verify(categoryPersistencePort).saveCategory(newCategory);
    }
}