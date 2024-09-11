package com.Emazon.stock_service.Infrastructure.ExceptionHandler;

import com.Emazon.stock_service.Domain.Exception.*;
import com.Emazon.stock_service.Infrastructure.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_NOT_FOUND.getMessage()));
    }


    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException missingAttributeException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<Map<String, String>> handleMissingAttributeException(
            MissingAttributeException missingAttributeException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, missingAttributeException.getMessage()));
    }

    @ExceptionHandler(InvalidLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLengthException(
            InvalidLengthException invalidLengthException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, invalidLengthException.getMessage()));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBrandAlreadyExistsException(
            BrandAlreadyExistsException brandAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBrandNotFoundException(
            BrandNotFoundException brandNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BRAND_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExistsException(
            ProductAlreadyExistsException productAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPageSizeException(
            InvalidPageSizeException invalidPageSizeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_PAGE_SIZE.getMessage()));
    }


    @ExceptionHandler(PageOutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> handlePageOutOfBoundsException(
            PageOutOfBoundsException pageOutOfBoundsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PAGE_OUT_OF_BOUNDS.getMessage()));
    }

    @ExceptionHandler(InvalidSortDirectionException.class)
    public ResponseEntity<Map<String, String>> handleInvalidSortDirectionException(
            InvalidSortDirectionException invalidSortDirectionException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_SORT_DIRECTION.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PRODUCT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidCategoriesException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoriesException(
            InvalidCategoriesException invalidCategoriesException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CATEGORIES_PER_PRODUCT.getMessage()));
    }
}
