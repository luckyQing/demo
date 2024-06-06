package com.collin.demo.api.version.exception;

public class ApiHandlerNotFoundException extends RuntimeException {

    public ApiHandlerNotFoundException(String message) {
        super(message);
    }

}