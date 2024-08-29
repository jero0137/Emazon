package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Domain.Model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandDtoMapper {
    Brand toBrand(BrandDto brandDto);
    BrandDto toBrandDto(Brand brand);
    List<BrandDto> toBrandsDto(List<Brand> brands);
}
