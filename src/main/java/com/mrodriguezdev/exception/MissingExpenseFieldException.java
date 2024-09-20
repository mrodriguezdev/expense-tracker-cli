package com.mrodriguezdev.exception;

public class MissingExpenseFieldException extends RuntimeException {
    public MissingExpenseFieldException(String message) {
        super(message);
    }
}
