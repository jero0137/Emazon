package com.Emazon.stock_service.Infrastructure.Output.jpa.adapter;

import com.Emazon.stock_service.Domain.Model.Article;
import com.Emazon.stock_service.Domain.SPI.IArticlePersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.ArticleAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ArticleEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.BrandEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.CategoryEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.ArticleEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IArticleRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ICategoryRepository categoryRepository;
    private final IBrandRepository brandRepository;
    private final ArticleEntityMapper articleEntityMapper;


    @Override
    public void saveArticle(Article article) {
        if(articleRepository.findByName(article.getName()).isPresent()){
            throw new ArticleAlreadyExistsException("Ya existe un articulo con ese nombre");
        }


        List<CategoryEntity> categoryEntities = article.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId()).orElseThrow(() -> new CategoryNotFoundException()))
                .collect(Collectors.toList());

        BrandEntity brandEntity = brandRepository.findById(article.getBrand().getId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);

        articleEntity.setCategories(categoryEntities);
        articleEntity.setBrand(brandEntity);

        articleRepository.save(articleEntity);

    }

    @Override
    public Article getArticle(Long id) {
        return articleEntityMapper.toArticle(articleRepository.findById(id).orElse(null));
    }
}
