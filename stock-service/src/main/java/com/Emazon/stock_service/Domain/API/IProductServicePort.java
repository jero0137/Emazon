package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.Model.Product;

public interface IProductServicePort {
    void saveArticle(Product product);
    PageCustom<Product> getProducts(Pagination pagination, String category, String brand);
    void addProductQuantity(Long productId, int quantity);
}
