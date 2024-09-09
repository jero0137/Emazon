package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.BrandDtoResponse;
import com.Emazon.stock_service.Domain.Model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BrandDtoResponseMapper {
    BrandDtoResponse toBrandDtoResponse(Brand brand);
}
