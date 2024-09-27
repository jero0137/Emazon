package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Dto.ProductDtoResponse;
import com.Emazon.stock_service.Application.Dto.SupplyDto;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Application.Mapper.ProductDtoMapper;
import com.Emazon.stock_service.Domain.API.IProductServicePort;
import com.Emazon.stock_service.Domain.Exception.InvalidSortDirectionException;
import com.Emazon.stock_service.Domain.Model.*;
import com.Emazon.stock_service.Utils.Constant;
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
    public PageCustom<ProductDtoResponse> getArticlesDto(int page, int size, String direction, String category, String brand) {
        if (direction == null || direction.isEmpty() ||
                (!Constant.SORT_DIRECTION_ASC.equalsIgnoreCase(direction) && !Constant.SORT_DIRECTION_DESC.equalsIgnoreCase(direction))) {
            throw new InvalidSortDirectionException();
        }
        Pagination pagination = new Pagination(page, size, "name", Sort.Direction.fromString(direction.toUpperCase()));
        return pageCustomDtoMapper.toProductDtoPageCustom(productServicePort.getProducts(pagination, category, brand));

    }

    @Override
    public void addProductQuantity(SupplyDto supplyDto) {
        productServicePort.addProductQuantity(supplyDto.getProductId(), supplyDto.getQuantity());
    }
}
