package com.Emazon.stock_service.Infrastructure.Output.jpa.adapter;

import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.BrandAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.BrandEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final PageEntityMapper pageEntityMapper;


    @Override
    public void saveBrand(Brand brand) {
        if(brandRepository.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException("Marca ya existe");
        }
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
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
