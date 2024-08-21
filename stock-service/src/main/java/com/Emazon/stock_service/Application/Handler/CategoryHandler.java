package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Mapper.CategoryDtoMapper;
import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Model.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    ICategoryServicePort categoryServicePort;
    CategoryDtoMapper categoryDtoMapper;

    @Override
    public void saveCategoryDto(CategoryDto categoryDto) {
        categoryServicePort.saveCategory(categoryDtoMapper.toCategory(categoryDto));
    }

    @Override
    public CategoryDto getCategoryDto(Long id) {
        Category category = categoryServicePort.getCategory(id);
        return categoryDtoMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategoriesDto() {
        return categoryDtoMapper.toCategoriesDto(categoryServicePort.getAllCategories());
    }

    @Override
    public void updateCategoryDto(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toCategory(categoryDto);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteCategoryDto(Long id) {
        categoryServicePort.deleteCategory(id);
    }
}
