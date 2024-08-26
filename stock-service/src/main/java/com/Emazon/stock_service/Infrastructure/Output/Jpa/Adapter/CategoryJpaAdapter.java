package com.Emazon.stock_service.Infrastructure.Output.Jpa.Adapter;

import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import com.Emazon.stock_service.Infrastructure.Exception.NoDataFoundException;
import com.Emazon.stock_service.Infrastructure.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Entity.CategoryEntity;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Mapper.CategoryEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.Jpa.Repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryByName(String name) {
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> getCategories(Pagination pagination) {
        PageRequest pageRequest = PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(pagination.getDirection(), pagination.getSort())
        );
        Page<CategoryEntity> page = categoryRepository.findAll(pageRequest);
        if(pagination.getPage() >= page.getTotalPages()){
            throw new PageOutOfBoundsException();
        }
        List<CategoryEntity> categoryEntities = page.getContent();

        if(categoryEntities.isEmpty()){
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategories(categoryEntities);
    }
}
