package com.example.campuschool_backend.exception;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException() {
    }

    public DuplicateIdException(String message) {
        super(message);
    }

    public DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateIdException(Throwable cause) {
        super(cause);
    }

    public DuplicateIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
