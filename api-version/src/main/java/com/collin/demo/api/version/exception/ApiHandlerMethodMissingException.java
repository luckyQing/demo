package com.collin.demo.api.version.exception;

public class ApiHandlerMethodMissingException extends RuntimeException {

    public ApiHandlerMethodMissingException(String message) {
        super(message);
    }

}