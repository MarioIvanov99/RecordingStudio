package com.studio.exception;

public class InvalidStudioOperationException extends RuntimeException {
    public InvalidStudioOperationException(String message) {
        super(message);
    }
}