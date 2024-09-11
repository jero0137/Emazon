package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.*;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public class PageCustomDtoMapper {

    CategoryDtoResponseMapper CATEGORY_DTORESPONSE_MAPPER = Mappers.getMapper(CategoryDtoResponseMapper.class);
    BrandDtoResponseMapper BRAND_DTORESPONSE_MAPPER = Mappers.getMapper(BrandDtoResponseMapper.class);
    ProductDtoResponseMapper PRODUCT_DTORESPONSE_MAPPER = Mappers.getMapper(ProductDtoResponseMapper.class);

    public PageCustom<CategoryDtoResponse> toCategoryDtoPageCustom(PageCustom<Category> page) {
        PageCustom<CategoryDtoResponse> pageCustom = new PageCustom<>();
        List<CategoryDtoResponse> categories = page.getContent().stream()
                .map(CATEGORY_DTORESPONSE_MAPPER::toCategoryDtoResponse)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<BrandDtoResponse> toBrandDtoPageCustom(PageCustom<Brand> page) {
        PageCustom<BrandDtoResponse> pageCustom = new PageCustom<>();
        List<BrandDtoResponse> brands = page.getContent().stream()
                .map(BRAND_DTORESPONSE_MAPPER::toBrandDtoResponse)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<ProductDtoResponse> toProductDtoPageCustom(PageCustom<Product> page) {
        PageCustom<ProductDtoResponse> pageCustom = new PageCustom<>();
        List<ProductDtoResponse> products = page.getContent().stream()
                .map(PRODUCT_DTORESPONSE_MAPPER::toProductDtoResponse)
                .toList();

        pageCustom.setContent(products);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

}
