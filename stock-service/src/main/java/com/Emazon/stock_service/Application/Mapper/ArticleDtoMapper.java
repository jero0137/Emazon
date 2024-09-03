package com.Emazon.stock_service.Application.Mapper;


import com.Emazon.stock_service.Application.Dto.ArticleDto;
import com.Emazon.stock_service.Domain.Model.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ArticleDtoMapper {
    Article toArticle(ArticleDto articleDto);
    ArticleDto toArticleDto(Article article);
}
