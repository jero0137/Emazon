package com.Emazon.stock_service.Infrastructure.Output.jpa.mapper;

import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Product;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.BrandEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.CategoryEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@RequiredArgsConstructor
public class PageEntityMapper {
    private final CategoryEntityMapper categoryEntityMapper;
    private final BrandEntityMapper brandEntityMapper;
    private final ProductEntityMapper productEntityMapper;

    public PageCustom<Category> toCategoryPageCustom(Page<CategoryEntity> page) {
        PageCustom<Category> pageCustom = new PageCustom<>();
        List<Category> categories = page.getContent().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setPageSize(page.getSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<Brand> toBrandPageCustom(Page<BrandEntity> page) {
        PageCustom<Brand> pageCustom = new PageCustom<>();
        List<Brand> brands = page.getContent().stream()
                .map(brandEntityMapper::toBrand)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setPageSize(page.getSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }

    public PageCustom<Product> toProductDtoPageCustom(Page<ProductEntity> page) {
        PageCustom<Product> pageCustom = new PageCustom<>();
        List<Product> products = page.getContent().stream()
                .map(productEntityMapper::toProduct)
                .toList();

        pageCustom.setContent(products);
        pageCustom.setPageSize(page.getSize());
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }
}
