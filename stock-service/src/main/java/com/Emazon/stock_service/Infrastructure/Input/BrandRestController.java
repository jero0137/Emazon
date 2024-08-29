package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Handler.IBrandHandler;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
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

    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandDto brandDto) {
        brandHandler.saveBrandDto(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public PageCustom<BrandDto> getBrands(@RequestParam int page, @RequestParam int size, @RequestParam Sort.Direction direction) {
        return brandHandler.getBrandsDto(page, size, direction);
    }
}
