package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.ICategoryServicePort;
import com.Emazon.stock_service.Domain.Exception.InvalidLengthException;
import com.Emazon.stock_service.Domain.Exception.InvalidPageSizeException;
import com.Emazon.stock_service.Domain.Exception.MissingAttributeException;
import com.Emazon.stock_service.Domain.Model.Category;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import com.Emazon.stock_service.Domain.Exception.PageOutOfBoundsException;
import com.Emazon.stock_service.Utils.Constant;


import java.util.ArrayList;
import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {



    private final ICategoryPersistencePort iCategoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort iCategoryPersistencePort) {
        this.iCategoryPersistencePort = iCategoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        List<String> missingAttributes = new ArrayList<>();

        if(category.getDescription().isEmpty() || category.getDescription() == null){
            missingAttributes.add("The category must have a description");
        }
        if(category.getName().isEmpty() || category.getName() == null){
            missingAttributes.add("The category must have a name");
        }
        if(!missingAttributes.isEmpty()){
            throw new MissingAttributeException(missingAttributes.toString());
        }
        if(category.getName().length() > Constant.MAX_CATEGORY_NAME_LENGTH){
            throw new InvalidLengthException("Category name must have maximum "+Constant.MAX_CATEGORY_NAME_LENGTH + "characters");
        }
        if(category.getDescription().length() > Constant.MAX_CATEGORY_DESCRIPTION_LENGTH){
            throw new InvalidLengthException("Category description must have maximum "+Constant.MAX_CATEGORY_DESCRIPTION_LENGTH + "characters");
        }
        this.iCategoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category getCategory(Long id) {
        if(id == null){
            throw new MissingAttributeException("Category id is missing");
        }
        if(iCategoryPersistencePort.getCategory(id) == null){
            throw new CategoryNotFoundException();
        }
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
    public PageCustom<Category> getCategories(Pagination pagination) {
        if(pagination.getPage() < 0){
            throw new PageOutOfBoundsException();
        }
        if (pagination.getSize() <= 0){
            throw new InvalidPageSizeException();
        }
        return iCategoryPersistencePort.getCategories(pagination);
    }
}
