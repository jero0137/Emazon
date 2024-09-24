package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Dto.ProductDtoResponse;
import com.Emazon.stock_service.Application.Dto.SupplyDto;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import org.springframework.data.domain.Sort;

public interface IProductHandler {
    void saveArticleDto(ProductDto productDto);
    PageCustom<ProductDtoResponse> getArticlesDto(int page, int size, String direction, String category, String brand);
    void supplyProduct(SupplyDto supplyDto);
}
