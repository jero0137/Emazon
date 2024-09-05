package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Handler.IProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ProductRestController {
    private final IProductHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody ProductDto productDto) {
        articleHandler.saveArticleDto(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
