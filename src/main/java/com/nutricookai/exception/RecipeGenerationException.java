package com.nutricookai.exception;

public class RecipeGenerationException extends ApiException {
    public RecipeGenerationException(String message) {
        super(message, 500); // 500 Internal Server Error
    }
}
