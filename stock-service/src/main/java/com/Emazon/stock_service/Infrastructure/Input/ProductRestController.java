package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Handler.IProductHandler;
import com.Emazon.stock_service.Domain.Model.PageCustom;
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


    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody ProductDto productDto) {
        productHandler.saveArticleDto(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<PageCustom<ProductDto>> getArticles(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                              @RequestParam(defaultValue = "") String category,
                                                              @RequestParam(defaultValue = "") String brand) {
        return ResponseEntity.ok(productHandler.getArticlesDto(page, size, direction, category, brand));
    }
}
