package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.ArticleDto;
import com.Emazon.stock_service.Application.Handler.IArticleHandler;
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
public class ArticleRestController {
    private final IArticleHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveArticle(@RequestBody ArticleDto articleDto) {
        articleHandler.saveArticleDto(articleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
