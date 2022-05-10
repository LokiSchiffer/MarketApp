package com.lokischiffer.marketapp.logic.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private int status;
    private String message;
    private String developerMessage;
}
