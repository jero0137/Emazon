package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.ProductDtoResponse;
import com.Emazon.stock_service.Domain.Model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProductDtoResponseMapper {
    ProductDtoResponse toProductDtoResponse(Product product);
}
