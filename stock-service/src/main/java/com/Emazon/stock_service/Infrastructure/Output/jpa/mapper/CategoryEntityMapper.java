package com.Emazon.stock_service.Infrastructure.Output.jpa.mapper;

import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

    List<Category> toCategories(List<CategoryEntity> categoryEntities);
}
