package com.Emazon.stock_service.Domain.UseCase;

import com.Emazon.stock_service.Domain.API.IProductServicePort;
import com.Emazon.stock_service.Domain.Exception.*;
import com.Emazon.stock_service.Domain.Model.*;
import com.Emazon.stock_service.Domain.SPI.IProductPersistencePort;
import com.Emazon.stock_service.Domain.SPI.IBrandPersistencePort;
import com.Emazon.stock_service.Domain.SPI.ICategoryPersistencePort;
import com.Emazon.stock_service.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class ProductUseCase implements IProductServicePort {
    IProductPersistencePort articlePersistencePort;
    ICategoryPersistencePort categoryPersistencePort;
    IBrandPersistencePort brandPersistencePort;

    public ProductUseCase(IProductPersistencePort articlePersistencePort, ICategoryPersistencePort categoryPersistencePort, IBrandPersistencePort brandPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveArticle(Product product) {

        List<String> missingAttributes = getStrings(product);

        if(product == null){
            throw new IllegalArgumentException(Constant.PRODUCT_NO_NULL);
        }
        if(product.getName() == null || product.getName().isEmpty()){
            throw new MissingAttributeException(Constant.PRODUCT_MUST_HAVE_NAME);
        }
        if(product.getDescription() == null || product.getDescription().isEmpty()){
            throw new MissingAttributeException(Constant.PRODUCT_MUST_HAVE_DESCRIPTION);
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new MissingAttributeException(Constant.PRODUCT_MUST_HAVE_PRICE);
        }
        if (!missingAttributes.isEmpty()) {
            throw new MissingAttributeException(missingAttributes.toString());
        }
        if (product.getName().length() > Constant.MAX_PRODUCT_NAME_LENGTH) {
            throw new InvalidLengthException(Constant.INVALID_PRODUCT_NAME_LENGTH);
        }
        if (product.getDescription().length() > Constant.MAX_PRODUCT_DESCRIPTION_LENGTH) {
            throw new InvalidLengthException(Constant.INVALID_PRODUCT_DESCRIPTION_LENGTH);
        }
        if(product.getCategories().size() > Constant.MAX_CATEGORIES_PER_PRODUCT || product.getCategories().isEmpty()){
            throw new InvalidCategoriesException();
        }
        List<Category> categories = product.getCategories().stream()
                .map(category -> categoryPersistencePort.getCategory(category.getId()))
                .toList();
        product.setCategories(categories);

        Brand brand = brandPersistencePort.getBrand(product.getBrand().getId());
        product.setBrand(brand);

        articlePersistencePort.saveProduct(product);
    }

    @Override
    public PageCustom<Product> getProducts(Pagination pagination, String category, String brand) {
        if(pagination.getPage() < 0){
            throw new PageOutOfBoundsException();
        }
        if (pagination.getSize() <= 0){
            throw new InvalidPageSizeException();
        }

        return articlePersistencePort.getProducts(pagination, category, brand);
    }

    @Override
    public void addProductQuantity(Long productId, int quantity) {
  
        if(quantity < 0){
            throw new InvalidQuantitySuppliedException();
        }

        articlePersistencePort.addProductQuantity(productId, quantity);
    }

    private static List<String> getStrings(Product product) {
        List<String> missingAttributes = new ArrayList<>();
        if(product == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        if(product.getName() == null || product.getName().isEmpty()) {
            missingAttributes.add("Article name is missing");
        }
        if(product.getDescription() == null || product.getDescription().isEmpty()) {
            missingAttributes.add("Article description is missing");
        }
        if(product.getPrice() == null || product.getPrice() < 0) {
            missingAttributes.add("Article price is missing");
        }
        if(product.getQuantity() < 0) {
            missingAttributes.add("Article stock is missing");
        }
        return missingAttributes;
    }
}
