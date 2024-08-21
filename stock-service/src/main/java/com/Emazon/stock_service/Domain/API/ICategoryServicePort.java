package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.Category;

import java.util.List;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Long id);
}
