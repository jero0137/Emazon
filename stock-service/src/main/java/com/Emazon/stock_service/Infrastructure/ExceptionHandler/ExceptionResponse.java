package com.Emazon.stock_service.Infrastructure.ExceptionHandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("There is already a category with that name");

    private String message;

    ExceptionResponse(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
