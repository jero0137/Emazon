package com.Emazon.stock_service.Domain.Exception;

public class InvalidLengthException extends RuntimeException{

    public InvalidLengthException(String m) {
        super(m);
    }
}
