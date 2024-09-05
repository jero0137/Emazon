package com.Emazon.stock_service.Infrastructure.Output.jpa.adapter;

import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.BrandAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Exception.BrandNotFoundException;
import com.Emazon.stock_service.Infrastructure.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.BrandEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.BrandEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


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
        return brandEntityMapper.toBrand(brandRepository.findById(id).orElseThrow(BrandNotFoundException::new));
    }


    @Override
    public void updateBrand(Brand brand) {

    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public PageCustom<Brand> getBrands(Pagination pagination) {
        if(pagination.getPage() < 0){
            throw new PageOutOfBoundsException();
        }
        PageRequest pageRequest = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(pagination.getDirection(), pagination.getSort())
        );
        Page<BrandEntity> page = brandRepository.findAll(pageRequest);
        if(pagination.getPage() >= page.getTotalPages()){
            throw new PageOutOfBoundsException();
        }
        return pageEntityMapper.toBrandPageCustom(page);
    }
}
