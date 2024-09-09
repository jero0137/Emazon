package com.Emazon.stock_service.Infrastructure.ExceptionHandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("There is already a category with that name"),
    CATEGORY_NOT_FOUND("Category not found"),
    NO_DATA_FOUND("No data found"),
    PAGE_OUT_OF_BOUNDS("Page out of bounds"),
    INVALID_PAGE_SIZE("Invalid page size"),
    INVALID_SORT_DIRECTION("Invalid sort direction"),
    BRAND_ALREADY_EXISTS("There is already a brand with that name"),
    BRAND_NOT_FOUND("Brand not found"),

    PRODUCT_ALREADY_EXISTS("There is already a product with that name"),
    PRODUCT_NOT_FOUND("Product not found");

    private final String message;

    ExceptionResponse(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
