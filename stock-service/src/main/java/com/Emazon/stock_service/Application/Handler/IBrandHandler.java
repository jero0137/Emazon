package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.BrandDto;

public interface IBrandHandler {
    void saveBrandDto(BrandDto brandDto);
    void updateBrand(String name, String description);
    void deleteBrand(String name);
    void getBrand(String name);
    void getAllBrands();
}
