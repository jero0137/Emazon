package com.Emazon.stock_service.Infrastructure.Configuration;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Domain.UseCase.CategoryUseCase;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Adapter.CategoryJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Mapper.CategoryEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Repository.ICategoryRepository;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final CategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    //Cuando necesitemos utilizar algo que implemente la interfaz ICategoryPersistencePort, Spring nos devolverá una instancia de CategoryJpaAdapter
    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    //Cuando necesitemos utilizar algo que implemente la interfaz ICategoryServicePort, Spring nos devolverá una instancia de CategoryUseCase
    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}
