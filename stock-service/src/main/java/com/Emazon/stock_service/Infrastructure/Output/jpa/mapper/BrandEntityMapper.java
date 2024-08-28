package com.Emazon.stock_service.Infrastructure.Output.jpa.mapper;

import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
    List<Brand> toBrands(List<BrandEntity> brandEntities);
}
