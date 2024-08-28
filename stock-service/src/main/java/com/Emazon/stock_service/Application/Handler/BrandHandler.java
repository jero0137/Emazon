package com.Emazon.stock_service.Application.Handler;


import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Mapper.BrandDtoMapper;
import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandDtoMapper brandDtoMapper;

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
    public void getAllBrands() {

    }
}
