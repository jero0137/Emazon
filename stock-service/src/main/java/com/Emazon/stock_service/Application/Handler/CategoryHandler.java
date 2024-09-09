package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Dto.CategoryDtoResponse;
import com.Emazon.stock_service.Application.Mapper.CategoryDtoMapper;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;
    private final PageCustomDtoMapper pageCustomDtoMapper;


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
    public void updateCategoryDto(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toCategory(categoryDto);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteCategoryDto(Long id) {
        categoryServicePort.deleteCategory(id);
    }

    @Override
    public PageCustom<CategoryDtoResponse> getCategoriesDto(int page, int size, Sort.Direction direction) {
        Pagination pagination = new Pagination(page, size, "name", direction);
        return pageCustomDtoMapper.toCategoryDtoPageCustom(categoryServicePort.getCategories(pagination));
    }
}
