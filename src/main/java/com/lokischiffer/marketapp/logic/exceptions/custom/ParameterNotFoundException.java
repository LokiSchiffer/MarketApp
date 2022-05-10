package com.lokischiffer.marketapp.logic.exceptions.custom;

public class ParameterNotFoundException extends RuntimeException {

    public ParameterNotFoundException() {
        super();
    }

    public ParameterNotFoundException(String message) {
        super(message);
    }
}
