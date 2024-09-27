package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.Model.Product;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    Product getArticle(Long id);
    PageCustom<Product> getProducts(Pagination pagination, String category, String brand);
    void addProductQuantity(Long productId, int quantity);
}
