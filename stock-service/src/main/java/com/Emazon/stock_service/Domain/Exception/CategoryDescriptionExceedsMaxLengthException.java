package com.Emazon.stock_service.Domain.Exception;

public class CategoryDescriptionExceedsMaxLengthException extends RuntimeException{
    public CategoryDescriptionExceedsMaxLengthException(int maxLength) {
        super("The name exceeds the maximum length of"  + maxLength + "characters.");
    }
}
