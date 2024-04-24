package com.example.campuschool_backend.exception;

public class InvalidExtensionException extends RuntimeException{
    public InvalidExtensionException() {
    }

    public InvalidExtensionException(String message) {
        super(message);
    }

    public InvalidExtensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExtensionException(Throwable cause) {
        super(cause);
    }

    public InvalidExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
