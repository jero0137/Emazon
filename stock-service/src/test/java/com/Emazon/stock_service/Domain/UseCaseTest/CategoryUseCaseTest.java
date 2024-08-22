package com.Emazon.stock_service.Domain.UseCaseTest;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Exception.CategoryDescriptionIsEmptyException;
import com.Emazon.stock_service.Domain.Exception.CategoryNameExceedsMaxLengthException;
import com.Emazon.stock_service.Domain.Exception.CategoryNameIsEmptyException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;

import com.Emazon.stock_service.Domain.UseCase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort iCategoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Category category = new Category(1L, "Valid Name", "");
        assertThrows(CategoryDescriptionIsEmptyException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        Category category = new Category(1L,"", "Valid Description");
        assertThrows(CategoryNameIsEmptyException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenNameExceedsMaxLength() {
        String longName = "A".repeat(51); // assuming 51 characters exceeds the limit
        Category category = new Category(1L ,longName, "Valid Description");
        assertThrows(CategoryNameExceedsMaxLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        String longDescription = "A".repeat(91); // assuming 91 characters exceeds the limit
        Category category = new Category(1L, "Valid Name", longDescription);
        assertThrows(CategoryNameExceedsMaxLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    public void shouldSaveCategorySuccessfullyWhenValid() {
        Category category = new Category(1L, "Valid Name", "Valid Description");

        // Configura el mock para que no haga nada cuando se llame a saveCategory
        doNothing().when(iCategoryPersistencePort).saveCategory(category);

        // Llama al método del caso de uso que debería interactuar con el mock
        categoryUseCase.saveCategory(category);

        // Verifica que el método saveCategory del mock se haya llamado correctamente
        verify(iCategoryPersistencePort).saveCategory(category);
    }
}
