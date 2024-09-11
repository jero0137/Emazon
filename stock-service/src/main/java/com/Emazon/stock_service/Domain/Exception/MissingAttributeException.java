package com.Emazon.stock_service.Domain.Exception;

public class MissingAttributeException extends RuntimeException{

    public MissingAttributeException(String message) {
        super(message);
    }
}
