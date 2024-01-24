package com.example.backend.handler;

import com.example.backend.services.exception.RequiredFieldMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RequiredFieldMissingHandler {
    @ExceptionHandler({RequiredFieldMissingException.class })
    public ResponseEntity<ErrorResponse> requiredFieldMissingException(RequiredFieldMissingException e) {

        ErrorResponse error = new ErrorResponse();
        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(LocalDateTime.now());
        error.getMessages().add(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
