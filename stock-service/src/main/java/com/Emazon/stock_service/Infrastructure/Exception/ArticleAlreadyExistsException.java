package com.Emazon.stock_service.Infrastructure.Exception;

public class ArticleAlreadyExistsException extends RuntimeException {
    public ArticleAlreadyExistsException(String message) {
        super(message);
    }
}
