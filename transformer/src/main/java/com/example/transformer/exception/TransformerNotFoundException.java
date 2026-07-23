package com.example.transformer.exception;

public class TransformerNotFoundException extends RuntimeException {
    public TransformerNotFoundException(String message){
        super(message);
    }
}
