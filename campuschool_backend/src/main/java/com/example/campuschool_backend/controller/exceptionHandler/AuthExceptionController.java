package com.example.campuschool_backend.controller.exceptionHandler;

import com.example.campuschool_backend.exception.ErrorResult;
import com.example.campuschool_backend.exception.NoUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult InvalidId(MethodArgumentNotValidException e) {
        return new ErrorResult("BAD", e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoUserException.class)
    public ErrorResult NoUserException(NoUserException e) {
        return new ErrorResult("BAD", e.getMessage());
    }
}
