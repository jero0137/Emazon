package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Mapper.ProductDtoMapper;
import com.Emazon.stock_service.Domain.API.IProductServicePort;
import com.Emazon.stock_service.Domain.Model.Product;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ProductHandler implements IProductHandler {



    private final IProductServicePort articleServicePort;
    private final ProductDtoMapper productDtoMapper;


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

        articleServicePort.saveArticle(product);

    }
}
