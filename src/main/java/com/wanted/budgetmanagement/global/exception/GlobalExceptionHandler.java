package com.wanted.budgetmanagement.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorException> ErrorExceptionHandle(ErrorException e) {
        log.error("ErrorException {}", e.getMessage());
        return ResponseEntity.status(e.getHttpStatus())
                .body(e);
    }
}
