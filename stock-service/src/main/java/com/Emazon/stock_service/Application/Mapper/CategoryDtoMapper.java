package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Domain.Model.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    List<Category> toCategories(List<CategoryDto> categoryDtos);
    List<CategoryDto> toCategoriesDto(List<Category> categories);
}
