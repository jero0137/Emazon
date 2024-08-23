package com.Emazon.stock_service.Domain.Exception;

public class InvalidLengthException extends RuntimeException{
    String message;

    public InvalidLengthException(String m) {
        message = m;
    }
}
