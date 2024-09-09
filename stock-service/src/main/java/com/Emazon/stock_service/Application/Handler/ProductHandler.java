package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Application.Mapper.ProductDtoMapper;
import com.Emazon.stock_service.Domain.API.IProductServicePort;
import com.Emazon.stock_service.Domain.Model.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ProductHandler implements IProductHandler {



    private final IProductServicePort productServicePort;
    private final ProductDtoMapper productDtoMapper;
    private final PageCustomDtoMapper pageCustomDtoMapper;

    @Override
    public void saveArticleDto(ProductDto productDto) {
        Product product = productDtoMapper.toProduct(productDto);

        List<Category> categories = productDto.getCategoriesIds().stream()
                .map(categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }).toList();

        product.setCategories(categories);

        Brand brand = new Brand();
        brand.setId(productDto.getBrandId());
        product.setBrand(brand);

        productServicePort.saveArticle(product);

    }

    @Override
    public PageCustom<ProductDto> getArticlesDto(int page, int size, Sort.Direction direction, String category, String brand) {
        Pagination pagination = new Pagination(page, size, "name", direction);
        return pageCustomDtoMapper.toProductDtoPageCustom(productServicePort.getProducts(pagination, category, brand));

    }
}
