package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void shouldSaveBrandWhenNotExists() {
        Brand newBrand = new Brand(null, "Haceb", "Todo lo relacionado a ollas");
        Mockito.doNothing().when(brandPersistencePort).saveBrand(newBrand);
        brandUseCase.saveBrand(newBrand);
        Mockito.verify(brandPersistencePort).saveBrand(newBrand);
    }
}