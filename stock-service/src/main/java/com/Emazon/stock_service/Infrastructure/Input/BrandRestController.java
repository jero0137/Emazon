package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
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
}
