package com.collin.demo.api.version.exception;

public class DuplicateApiHandlerException extends RuntimeException {

    public DuplicateApiHandlerException(String message) {
        super(message);
    }

}