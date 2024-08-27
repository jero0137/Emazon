package com.Emazon.stock_service.Infrastructure.Configuration;

import com.Emazon.stock_service.Application.Mapper.CategoryDtoMapper;
import com.Emazon.stock_service.Application.Mapper.PageCustomDtoMapper;
import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.UseCase.CategoryUseCase;
import com.Emazon.stock_service.Infrastructure.Output.jpa.adapter.CategoryJpaAdapter;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
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

    @Bean
    public PageEntityMapper pageEntityMapper() {
        return new PageEntityMapper(categoryEntityMapper);
    }

    @Bean
    public PageCustomDtoMapper pageDtoMapper() {
        return new PageCustomDtoMapper(categoryDtoMapper);
    }

    //Cuando necesitemos utilizar algo que implemente la interfaz ICategoryPersistencePort, Spring nos devolverá una instancia de CategoryJpaAdapter
    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper, pageEntityMapper());
    }

    //Cuando necesitemos utilizar algo que implemente la interfaz ICategoryServicePort, Spring nos devolverá una instancia de CategoryUseCase
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}
