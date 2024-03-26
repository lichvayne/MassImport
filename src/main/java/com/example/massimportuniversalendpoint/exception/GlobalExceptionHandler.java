package com.example.massimportuniversalendpoint.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MassImportException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(MassImportException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), exception.getCode());
        return new ResponseEntity<>(exceptionResponse, exception.getCode());
    }
}
