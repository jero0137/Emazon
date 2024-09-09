package com.Emazon.stock_service.Infrastructure.Configuration;


import com.Emazon.stock_service.Application.Mapper.BrandDtoMapper;
import com.Emazon.stock_service.Application.Mapper.CategoryDtoMapper;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Application.Mapper.ProductDtoMapper;
import com.Emazon.stock_service.Domain.API.IProductServicePort;
import com.Emazon.stock_service.Domain.API.IBrandServicePort;
import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.SPI.IProductPersistencePort;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.UseCase.ProductUseCase;
import com.Emazon.stock_service.Domain.UseCase.BrandUseCase;
import com.Emazon.stock_service.Domain.UseCase.CategoryUseCase;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.ProductJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.BrandJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.CategoryJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.ProductEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.BrandEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IProductRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
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

    private final ProductEntityMapper productEntityMapper;
    private final IProductRepository articleRepository;
    private final ProductDtoMapper productDtoMapper;

    @Bean
    public PageEntityMapper pageEntityMapper() {
        return new PageEntityMapper(categoryEntityMapper, brandEntityMapper, productEntityMapper);
    }


    @Bean
    public PageCustomDtoMapper pageDtoMapper() {
        return new PageCustomDtoMapper(categoryDtoMapper, brandDtoMapper, productDtoMapper);
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
    public IProductPersistencePort productPersistencePort(){
        return new ProductJpaAdapter(articleRepository, categoryRepository, brandRepository, productEntityMapper, pageEntityMapper());
    }

    @Bean
    public IProductServicePort articleServicePort(){
        return new ProductUseCase(productPersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }

}
