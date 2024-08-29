package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Domain.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {

    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    List<Category> toCategories(List<CategoryDto> categoryDtos);
    List<CategoryDto> toCategoriesDto(List<Category> categories);
}
