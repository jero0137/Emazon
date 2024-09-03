package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.Article;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    Article getArticle(Long id);
}
