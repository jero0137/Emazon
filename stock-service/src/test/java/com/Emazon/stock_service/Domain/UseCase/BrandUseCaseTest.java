package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.InvalidPageSizeException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.Exception.PageOutOfBoundsException;
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
class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenBrandNameIsNull() {
        Brand brand = new Brand(1L, null, "Description");
        assertThrows(MissingAttributeException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldThrowExceptionWhenBrandNameIsEmpty() {
        Brand brand = new Brand(1L, "", "Description");
        assertThrows(MissingAttributeException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldThrowExceptionWhenBrandNameIsTooLong() {
        Brand brand = new Brand(1L, "A".repeat(51), "Description");
        assertThrows(InvalidLengthException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldThrowExceptionWhenBrandDescriptionIsEmpty() {
        Brand brand = new Brand(1L, "Name", "");
        assertThrows(MissingAttributeException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldThrowExceptionWhenBrandDescriptionIsNull() {
        Brand brand = new Brand(1L, "Name", null);
        assertThrows(MissingAttributeException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldThrowExceptionWhenBrandDescriptionIsTooLong() {
        Brand brand = new Brand(1L, "Name", "A".repeat(101));
        assertThrows(InvalidLengthException.class, () -> brandUseCase.saveBrand(brand));
    }

    @Test
    void shouldSaveBrandWhenNotExists() {
        Brand newBrand = new Brand(null, "Haceb", "Todo lo relacionado a ollas");
        Mockito.doNothing().when(brandPersistencePort).saveBrand(newBrand);
        brandUseCase.saveBrand(newBrand);
        Mockito.verify(brandPersistencePort).saveBrand(newBrand);
    }

    @Test
    void shouldReturnPageCustomWhenValidPagination() {
        Pagination pagination = new Pagination(0, 10, "name", Sort.Direction.ASC);
        PageCustom<Brand> expectedPage = new PageCustom<>(List.of(new Brand(1L, "Nike", "Sportswear")), 0, 10, 1, 1);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);
        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
    }

    @Test
    void shouldReturnEmptyPageCustomWhenNoBrands() {
        Pagination pagination = new Pagination(0, 10, "name", Sort.Direction.ASC);
        PageCustom<Brand> expectedPage = new PageCustom<>(List.of(), 0, 10, 0, 0);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);
        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
    }

    @Test
    void shouldThrowExceptionWhenPageOutOfBounds() {
        Pagination pagination = new Pagination(10, 10, "name", Sort.Direction.ASC);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenThrow(new PageOutOfBoundsException());
        assertThrows(PageOutOfBoundsException.class, () -> brandUseCase.getBrands(pagination));
    }

    @Test
    void shouldReturnBrandsInAscendingOrder() {
        int page = 0;
        int size = 10;
        String sort = "name";
        Sort.Direction direction = Sort.Direction.ASC;

        Pagination pagination = new Pagination(page, size, sort, direction);
        List<Brand> brands = List.of(
                new Brand(1L, "Adidas", "Sportswear"),
                new Brand(2L, "Nike", "Sportswear"),
                new Brand(3L, "Puma", "Sportswear")
        );
        PageCustom<Brand> expectedPage = new PageCustom<>(brands, 0, 10, 3, 1);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);

        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
        assertEquals("Adidas", result.getContent().get(0).getName());
        assertEquals("Nike", result.getContent().get(1).getName());
        assertEquals("Puma", result.getContent().get(2).getName());
    }

    @Test
    void shouldReturnBrandsInDescendingOrder() {
        int page = 0;
        int size = 10;
        String sort = "name";
        Sort.Direction direction = Sort.Direction.DESC;

        Pagination pagination = new Pagination(page, size, sort, direction);
        List<Brand> brands = List.of(
                new Brand(3L, "Puma", "Sportswear"),
                new Brand(2L, "Nike", "Sportswear"),
                new Brand(1L, "Adidas", "Sportswear")
        );
        PageCustom<Brand> expectedPage = new PageCustom<>(brands, 0, 10, 3, 1);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);

        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
        assertEquals("Puma", result.getContent().get(0).getName());
        assertEquals("Nike", result.getContent().get(1).getName());
        assertEquals("Adidas", result.getContent().get(2).getName());
    }

    @Test
    void shouldThrowExceptionWhenBrandIsNull() {
        assertThrows(IllegalArgumentException.class, () -> brandUseCase.saveBrand(null));
    }

    @Test
    void shouldThrowExceptionWhenPageIsNegative() {
        Pagination pagination = new Pagination(-1, 10, "name", Sort.Direction.ASC);
        assertThrows(PageOutOfBoundsException.class, () -> brandUseCase.getBrands(pagination));
    }

    @Test
    void shouldThrowExceptionWhenPageSizeIsZero() {
        Pagination pagination = new Pagination(0, 0, "name", Sort.Direction.ASC);
        assertThrows(InvalidPageSizeException.class, () -> brandUseCase.getBrands(pagination));
    }

    @Test
    void shouldReturnBrandsWhenPaginationIsValid() {
        Pagination pagination = new Pagination(0, 10, "name", Sort.Direction.ASC);
        List<Brand> brands = List.of(new Brand(1L, "Nike", "Sportswear"));
        PageCustom<Brand> expectedPage = new PageCustom<>(brands, 0, 10, 1, 1);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);
        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
    }

    @Test
    void shouldReturnEmptyPageWhenNoBrands() {
        Pagination pagination = new Pagination(0, 10, "name", Sort.Direction.ASC);
        PageCustom<Brand> expectedPage = new PageCustom<>(List.of(), 0, 10, 0, 0);
        Mockito.when(brandPersistencePort.getBrands(pagination)).thenReturn(expectedPage);
        PageCustom<Brand> result = brandUseCase.getBrands(pagination);
        assertEquals(expectedPage, result);
    }

}