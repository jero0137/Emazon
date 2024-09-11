package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Dto.ProductDtoResponse;
import com.Emazon.stock_service.Application.Handler.IProductHandler;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {
    private final IProductHandler productHandler;

    @Operation(summary = "Save a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody ProductDto productDto) {
        productHandler.saveArticleDto(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all articles"),
            @ApiResponse(responseCode = "404", description = "Articles not found")
    })
    @GetMapping("/")
    public ResponseEntity<PageCustom<ProductDtoResponse>> getArticles(@RequestParam(defaultValue = Constant.DEFAULT_PAGE) int page,
                                                                      @RequestParam(defaultValue = Constant.DEFAULT_SIZE) int size,
                                                                      @RequestParam(defaultValue = Constant.DEFAULT_SORT_DIRECTION) String direction,
                                                                      @RequestParam(defaultValue = "") String category,
                                                                      @RequestParam(defaultValue = "") String brand) {
        return ResponseEntity.ok(productHandler.getArticlesDto(page, size, direction, category, brand));
    }
}
