package com.example.backend.services.exception;

public class RequiredFieldMissingException extends RuntimeException{
    public RequiredFieldMissingException(String message){
        super(message);
    }
}
