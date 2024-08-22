package com.Emazon.stock_service.Domain.Exception;

public class CategoryNameIsEmptyException extends RuntimeException{
    public CategoryNameIsEmptyException() {
        super("The category must have a name.");
    }
}
