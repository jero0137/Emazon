package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
