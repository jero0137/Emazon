package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Dto.BrandDtoResponse;
import com.Emazon.stock_service.Application.Handler.IBrandHandler;
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
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Save a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandDto brandDto) {
        brandHandler.saveBrandDto(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all brands"),
            @ApiResponse(responseCode = "404", description = "Brands not found")
    })
    @GetMapping("/")
    public PageCustom<BrandDtoResponse> getBrands(@RequestParam(defaultValue = Constant.DEFAULT_PAGE) int page,
                                                  @RequestParam(defaultValue = Constant.DEFAULT_SIZE) int size,
                                                  @RequestParam(defaultValue = Constant.DEFAULT_SORT_DIRECTION) String direction) {
        return brandHandler.getBrandsDto(page, size, direction);
    }
}
