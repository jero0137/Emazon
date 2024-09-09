package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Dto.CategoryDtoResponse;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import org.springframework.data.domain.Sort;


public interface ICategoryHandler {
    void saveCategoryDto(CategoryDto categoryDto);
    CategoryDto getCategoryDto(Long id);

    void updateCategoryDto(CategoryDto categoryDto);
    void deleteCategoryDto(Long id);
    PageCustom<CategoryDtoResponse> getCategoriesDto(int page, int size, Sort.Direction direction);
}
