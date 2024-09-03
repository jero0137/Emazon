package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.Article;

public interface IArticleServicePort {
    void saveArticle(Article article);

}
