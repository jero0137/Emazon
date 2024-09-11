package com.Emazon.stock_service.Infrastructure.Output.jpa.mapper;

import com.Emazon.stock_service.Domain.Model.Product;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
