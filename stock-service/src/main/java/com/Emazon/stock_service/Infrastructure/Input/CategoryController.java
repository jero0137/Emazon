package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Dto.CategoryDtoResponse;
import com.Emazon.stock_service.Application.Handler.ICategoryHandler;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Save a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@Valid
            @RequestBody CategoryDto categoryDto) {
        categoryHandler.saveCategoryDto(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all categories"),
            @ApiResponse(responseCode = "404", description = "Categories not found")
    })
    @GetMapping("/")
    public PageCustom<CategoryDtoResponse> getCategories(@RequestParam(defaultValue = Constant.DEFAULT_PAGE) int page,
                                                         @RequestParam(defaultValue = Constant.DEFAULT_SIZE) int size,
                                                         @RequestParam(defaultValue = Constant.DEFAULT_SORT_DIRECTION) String direction) {
        return categoryHandler.getCategoriesDto(page, size, direction );
    }

    @Operation(summary = "Get a category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(
            @PathVariable Long id) {
        return categoryHandler.getCategoryDto(id);
    }

}
