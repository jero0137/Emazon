package com.Emazon.stock_service.Domain.SPI;

import com.Emazon.stock_service.Domain.Model.Product;

public interface IProductPersistencePort {
    void saveArticle(Product product);
    Product getArticle(Long id);
}
