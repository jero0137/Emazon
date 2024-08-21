package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
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
