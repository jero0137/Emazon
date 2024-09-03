package com.Emazon.stock_service.Infrastructure.Output.jpa.mapper;

import com.Emazon.stock_service.Domain.Model.Article;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ArticleEntityMapper {

    ArticleEntity toArticleEntity(Article article);
    Article toArticle(ArticleEntity articleEntity);
}
