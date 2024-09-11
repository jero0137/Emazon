package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.InvalidPageSizeException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Domain.Model.*;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.SPI.IProductPersistencePort;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private IProductPersistencePort productPersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ProductUseCase productUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldSaveArticleWhenAllCategoriesExist() {
        Product product = new Product(1L, "Product Name", "Description", 100, 1000000L, List.of(new Category(1L, "Category", "Description")), new Brand(1L, "Brand", "description"));
        when(categoryPersistencePort.getCategory(1L)).thenReturn(new Category(1L, "Category", "Description"));
        when(brandPersistencePort.getBrand(1L)).thenReturn(new Brand(1L, "Brand", "Description"));
        Mockito.doNothing().when(productPersistencePort).saveArticle(product);
        productUseCase.saveArticle(product);
        Mockito.verify(productPersistencePort).saveArticle(product);
    }

    @Test
    void shouldThrowExceptionWhenProductNameIsTooLong() {
        Product product = new Product(1L, "A".repeat(51), "Description", 100, 1000L, List.of(new Category(1L, "Category", "Description")), new Brand(1L, "Brand", "Description"));
        assertThrows(InvalidLengthException.class, () -> productUseCase.saveArticle(product));
    }

    @Test
    void shouldThrowExceptionWhenProductDescriptionIsTooLong() {
        Product product = new Product(1L, "Product Name", "A".repeat(121), 10, 1023232L, List.of(new Category(1L, "Category", "Description")), new Brand(1L, "Brand", "Description"));
        assertThrows(InvalidLengthException.class, () -> productUseCase.saveArticle(product));
    }

    @Test
    void shouldThrowExceptionWhenProductHasMissingAttributes() {
        Product product = new Product(1L, null, "Description", 100, 20000L, List.of(new Category(1L, "Category", "Description")), new Brand(1L, "Brand", "Description"));
        assertThrows(MissingAttributeException.class, () -> productUseCase.saveArticle(product));
    }

    @Test
    void shouldReturnProductsWhenPaginationAndFiltersAreValid() {
        Pagination pagination = new Pagination(0, 10, "name", Sort.Direction.ASC);
        String category = "Electronics";
        String brand = "BrandA";

        PageCustom<Product> expectedPage = new PageCustom<>(List.of(new Product(1L, "Product Name", "Description", 100, 1000000L, List.of(new Category(1L, "Electronics", "Description")), new Brand(1L, "BrandA", "Description"))), 0, 10, 1, 1);

        when(productPersistencePort.getProducts(pagination, category, brand)).thenReturn(expectedPage);

        PageCustom<Product> result = productUseCase.getProducts(pagination, category, brand);

        assertEquals(expectedPage, result);
        verify(productPersistencePort).getProducts(pagination, category, brand);
    }

    @Test
    void shouldThrowPageOutOfBoundsExceptionWhenPageIsNegative() {
        Pagination pagination = new Pagination(-1, 10, "name", Sort.Direction.ASC);

        assertThrows(PageOutOfBoundsException.class, () -> productUseCase.getProducts(pagination, "Electronics", "BrandA"));
    }

    @Test
    void shouldThrowInvalidPageSizeExceptionWhenPageSizeIsZero() {
        Pagination pagination = new Pagination(0, 0, "name", Sort.Direction.ASC);

        assertThrows(InvalidPageSizeException.class, () -> productUseCase.getProducts(pagination, "Electronics", "BrandA"));
    }

    @Test
    void shouldThrowInvalidPageSizeExceptionWhenPageSizeIsNegative() {
        Pagination pagination = new Pagination(0, -1, "name", Sort.Direction.ASC);

        assertThrows(InvalidPageSizeException.class, () -> productUseCase.getProducts(pagination, "Electronics", "BrandA"));
    }

}