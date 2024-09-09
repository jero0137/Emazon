package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Product;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageCustomDtoMapper {

    private final CategoryDtoMapper categoryDtoMapper;
    private final BrandDtoMapper brandDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    public PageCustom<CategoryDto> toCategoryDtoPageCustom(PageCustom<Category> page) {
        PageCustom<CategoryDto> pageCustom = new PageCustom<>();
        List<CategoryDto> categories = page.getContent().stream()
                .map(categoryDtoMapper::toCategoryDto)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<BrandDto> toBrandDtoPageCustom(PageCustom<Brand> page) {
        PageCustom<BrandDto> pageCustom = new PageCustom<>();
        List<BrandDto> categories = page.getContent().stream()
                .map(brandDtoMapper::toBrandDto)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<ProductDto> toProductDtoPageCustom(PageCustom<Product> page) {
        PageCustom<ProductDto> pageCustom = new PageCustom<>();
        List<ProductDto> products = page.getContent().stream()
                .map(productDtoMapper::toProductDto)
                .toList();

        pageCustom.setContent(products);
        pageCustom.setPageSize(page.getPageSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

}
