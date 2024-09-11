package com.Emazon.stock_service.Application.Mapper;

import com.Emazon.stock_service.Application.Dto.CategoryDtoResponse;
import com.Emazon.stock_service.Domain.Model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CategoryDtoResponseMapper {
    CategoryDtoResponse toCategoryDtoResponse(Category category);
}
