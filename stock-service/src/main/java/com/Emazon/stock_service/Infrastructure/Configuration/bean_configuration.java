package com.Emazon.stock_service.Infrastructure.Configuration;

import com.Emazon.stock_service.Application.Mapper.ArticleDtoMapper;
import com.Emazon.stock_service.Application.Mapper.BrandDtoMapper;
import com.Emazon.stock_service.Application.Mapper.CategoryDtoMapper;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Domain.API.IArticleServicePort;
import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.SPI.IArticlePersistencePort;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.UseCase.ArticleUseCase;
import com.Emazon.stock_service.Domain.UseCase.BrandUseCase;
import com.Emazon.stock_service.Domain.UseCase.CategoryUseCase;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.ArticleJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.BrandJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.CategoryJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.ArticleEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.BrandEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IArticleRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.CategoryEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.ICategoryRepository;

@Configuration
@RequiredArgsConstructor
public class bean_configuration {

    private final CategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    private final BrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;
    private final BrandDtoMapper brandDtoMapper;

    private final ArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;
    private final ArticleDtoMapper articleDtoMapper;

    @Bean
    public PageEntityMapper pageEntityMapper() {
        return new PageEntityMapper(categoryEntityMapper, brandEntityMapper);
    }


    @Bean
    public PageCustomDtoMapper pageDtoMapper() {
        return new PageCustomDtoMapper(categoryDtoMapper, brandDtoMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper, pageEntityMapper());
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository, brandEntityMapper, pageEntityMapper());
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort(){
        return new ArticleJpaAdapter(articleRepository, categoryRepository, brandRepository,articleEntityMapper);
    }

    @Bean
    public IArticleServicePort articleServicePort(){
        return new ArticleUseCase(articlePersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }

}
