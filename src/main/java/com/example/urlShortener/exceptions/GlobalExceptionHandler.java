package com.example.urlShortener.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortenerException.class)
    public ResponseEntity<String> handleShortenerException(ShortenerException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getDescription());
    }
}
