package com.lokischiffer.marketapp.logic.exceptions.custom;

public class ConflictException extends RuntimeException {

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }
}
