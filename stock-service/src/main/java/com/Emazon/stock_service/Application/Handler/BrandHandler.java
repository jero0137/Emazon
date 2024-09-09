package com.Emazon.stock_service.Application.Handler;


import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Dto.BrandDtoResponse;
import com.Emazon.stock_service.Application.Mapper.BrandDtoMapper;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandDtoMapper brandDtoMapper;
    private final PageCustomDtoMapper pageCustomDtoMapper;

    @Override
    public void saveBrandDto(BrandDto brandDto) {
        brandServicePort.saveBrand(brandDtoMapper.toBrand(brandDto));
    }

    @Override
    public void updateBrand(String name, String description) {

    }

    @Override
    public void deleteBrand(String name) {

    }

    @Override
    public void getBrand(String name) {

    }

    @Override
    public PageCustom<BrandDtoResponse> getBrandsDto(int page, int size, Sort.Direction direction) {
        Pagination pagination = new Pagination(page, size, "name", direction);
        return pageCustomDtoMapper.toBrandDtoPageCustom(brandServicePort.getBrands(pagination));
    }
}
