package com.example.transformer.exception;

public class DuplicateSerialNumberException extends RuntimeException{
    public DuplicateSerialNumberException(String message){
        super(message);
    }
}
