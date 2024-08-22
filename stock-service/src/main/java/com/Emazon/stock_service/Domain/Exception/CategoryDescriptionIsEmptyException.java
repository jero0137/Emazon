package com.Emazon.stock_service.Domain.Exception;

public class CategoryDescriptionIsEmptyException extends RuntimeException {
    public CategoryDescriptionIsEmptyException() {
        super("The category must have a description.");
    }
}
