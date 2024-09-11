package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategoryById(Long id);
    void deleteCategoryByName(String name);
    PageCustom<Category> getCategories(Pagination pagination);
}
