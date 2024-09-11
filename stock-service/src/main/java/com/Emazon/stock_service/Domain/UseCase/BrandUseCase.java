package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.InvalidPageSizeException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandServicePort) {
        this.brandPersistencePort = brandServicePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        List<String> missingAttributes = new ArrayList<>();

        if(brand == null) {
            throw new IllegalArgumentException(Constant.BRAND_NO_NULL);
        }
        if(brand.getName() == null || brand.getName().isEmpty()) {
            missingAttributes.add(Constant.BRAND_MUST_HAVE_NAME);
        }
        if(brand.getDescription() == null || brand.getDescription().isEmpty()) {
            missingAttributes.add(Constant.BRAND_MUST_HAVE_DESCRIPTION);
        }
        if (!missingAttributes.isEmpty()) {
            throw new MissingAttributeException(missingAttributes.toString());
        }
        if(brand.getName().length() > 50) {
            throw new InvalidLengthException(Constant.INVALID_BRAND_NAME_LENGTH);
        }
        if(brand.getDescription().length() > 100) {
            throw new InvalidLengthException(Constant.INVALID_BRAND_DESCRIPTION_LENGTH);
        }
        this.brandPersistencePort.saveBrand(brand);
    }



    @Override
    public PageCustom<Brand> getBrands(Pagination pagination) {
        if(pagination.getPage() < 0){
            throw new PageOutOfBoundsException();
        }
        if (pagination.getSize() <= 0){
            throw new InvalidPageSizeException();
        }
        return brandPersistencePort.getBrands(pagination);
    }
}
