package com.example.massimportuniversalendpoint.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MassImportException extends RuntimeException {
    private HttpStatus code;
    public MassImportException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

}
