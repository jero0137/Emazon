package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandServicePort) {
        this.brandPersistencePort = brandServicePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if(brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        if(brand.getName() == null || brand.getName().isEmpty()) {
            throw new MissingAttributeException("Brand name cannot be null or empty");
        }
        if(brand.getName().length() > 50) {
            throw new InvalidLengthException("Brand name cannot be longer than 50 characters");
        }
        if(brand.getDescription() == null || brand.getDescription().isEmpty()) {
            throw new MissingAttributeException("Brand description cannot be null or empty");
        }
        if(brand.getDescription().length() > 100) {
            throw new InvalidLengthException("Brand description cannot be longer than 100 characters");
        }
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand getBrand(Long id) {
        return null;
    }

    @Override
    public List<Brand> getAllBrands() {
        return List.of();
    }

    @Override
    public void updateBrand(Brand brand) {

    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public PageCustom<Brand> getBrands(Pagination pagination) {
        return null;
    }
}
