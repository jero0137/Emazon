package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.CategoryDto;

import java.util.List;

public interface ICategoryHandler {
    void saveCategoryDto(CategoryDto categoryDto);
    CategoryDto getCategoryDto(Long id);
    List<CategoryDto> getAllCategoriesDto();
    void updateCategoryDto(CategoryDto categoryDto);
    void deleteCategoryDto(Long id);
}
