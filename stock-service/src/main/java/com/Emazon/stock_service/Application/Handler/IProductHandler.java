package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import org.springframework.data.domain.Sort;

public interface IProductHandler {
    void saveArticleDto(ProductDto productDto);
    PageCustom<ProductDto> getArticlesDto(int page, int size, Sort.Direction direction, String category, String brand);
}
