package com.Emazon.stock_service.Application.Handler;

import com.Emazon.stock_service.Application.Dto.ArticleDto;
import com.Emazon.stock_service.Application.Mapper.ArticleDtoMapper;
import com.Emazon.stock_service.Domain.API.IArticleServicePort;
import com.Emazon.stock_service.Domain.Model.Article;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {



    private final IArticleServicePort articleServicePort;
    private final ArticleDtoMapper articleDtoMapper;


    @Override
    public void saveArticleDto(ArticleDto articleDto) {
        Article article = articleDtoMapper.toArticle(articleDto);

        List<Category> categories = articleDto.getCategoriesIds().stream()
                .map(categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }).toList();

        article.setCategories(categories);

        Brand brand = new Brand();
        brand.setId(articleDto.getBrandId());
        article.setBrand(brand);

        articleServicePort.saveArticle(article);

    }
}
