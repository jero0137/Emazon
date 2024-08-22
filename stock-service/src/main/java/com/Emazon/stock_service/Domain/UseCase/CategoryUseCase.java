package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Exception.CategoryDescriptionIsEmptyException;
import com.Emazon.stock_service.Domain.Exception.CategoryNameExceedsMaxLengthException;
import com.Emazon.stock_service.Domain.Exception.CategoryNameIsEmptyException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;


import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 90;

    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        if(category.getDescription().isEmpty()){
            throw new CategoryDescriptionIsEmptyException();
        }
        if(category.getName().isEmpty()){
            throw new CategoryNameIsEmptyException();
        }
        if(category.getName().length() > MAX_NAME_LENGTH){
            throw new CategoryNameExceedsMaxLengthException(MAX_NAME_LENGTH);
        }
        if(category.getDescription().length() > MAX_DESCRIPTION_LENGTH){
            throw new CategoryNameExceedsMaxLengthException(MAX_DESCRIPTION_LENGTH);
        }
        this.iCategoryPersistencePort.saveCategory(category);
        return category;
    }

    @Override
    public Category getCategory(Long id) {
        return this.iCategoryPersistencePort.getCategory(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.iCategoryPersistencePort.getAllCategories();
    }

    @Override
    public void updateCategory(Category category) {
        this.iCategoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        this.iCategoryPersistencePort.deleteCategory(id);
    }
}
