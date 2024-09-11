package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Domain.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductDtoMapper {
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
