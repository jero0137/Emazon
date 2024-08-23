package com.Emazon.stock_service.Infrastructure.Output.Jpa.Adapter;

import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import com.Emazon.stock_service.Infrastructure.Exception.NoDataFoundException;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Entity.CategoryEntity;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Mapper.CategoryEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Repository.ICategoryRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if(categoryEntities.isEmpty()){
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategories(categoryEntities);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
