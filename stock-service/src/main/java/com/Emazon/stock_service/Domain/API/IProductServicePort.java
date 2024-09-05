package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.Product;

public interface IProductServicePort {
    void saveArticle(Product product);

}
