package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    Category getCategory(Long id);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(Long id);
    PageCustom<Category> getCategories(Pagination pagination);
}
