package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.Pagination;
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
        if(category.getDescription().isEmpty() || category.getDescription() == null){
            throw new MissingAttributeException("The category must have a description");
        }
        if(category.getName().isEmpty() || category.getName() == null){
            throw new MissingAttributeException("The category must have a name");
        }
        if(category.getName().length() > MAX_NAME_LENGTH){
            throw new InvalidLengthException("Category name must have maximum "+MAX_NAME_LENGTH + "characters");
        }
        if(category.getDescription().length() > MAX_DESCRIPTION_LENGTH){
            throw new InvalidLengthException("Category description must have maximum "+MAX_DESCRIPTION_LENGTH + "characters");
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
        this.iCategoryPersistencePort.deleteCategoryById(id);
    }

    @Override
    public List<Category> getCategories(Pagination pagination) {
        return iCategoryPersistencePort.getCategories(pagination);
    }
}
