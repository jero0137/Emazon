package com.Emazon.stock_service.Domain.Exception;

public class CategoryNameExceedsMaxLengthException extends RuntimeException{

    public CategoryNameExceedsMaxLengthException(int maxLength) {
        super("The name exceeds the maximum length of " + maxLength + " characters.");
    }
}
