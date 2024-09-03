package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.IArticleServicePort;
import com.Emazon.stock_service.Domain.Model.Article;
import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.IArticlePersistencePort;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleUseCase implements IArticleServicePort {
    private final IArticlePersistencePort articlePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IBrandPersistencePort brandPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }


    public void saveArticle(Article article) {

        List<Category> categories = article.getCategories().stream()
                .map(category -> categoryPersistencePort.getCategory(category.getId()))
                .collect(Collectors.toList());
        article.setCategories(categories);

        Brand brand = brandPersistencePort.getBrand(article.getBrand().getId());
        article.setBrand(brand);

        articlePersistencePort.saveArticle(article);
    }
}
