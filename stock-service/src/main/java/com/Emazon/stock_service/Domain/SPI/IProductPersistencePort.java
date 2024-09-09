package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.Model.Product;

public interface IProductPersistencePort {
    void saveArticle(Product product);
    Product getArticle(Long id);
    PageCustom<Product> getProducts(Pagination pagination);
}
