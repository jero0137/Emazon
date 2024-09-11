package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Dto.BrandDtoResponse;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import org.springframework.data.domain.Sort;

public interface IBrandHandler {
    void saveBrandDto(BrandDto brandDto);
    void updateBrand(String name, String description);
    void deleteBrand(String name);
    void getBrand(String name);
    PageCustom<BrandDtoResponse> getBrandsDto(int page, int size, String direction);
}
