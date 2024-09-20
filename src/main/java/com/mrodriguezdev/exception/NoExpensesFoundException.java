package com.mrodriguezdev.exception;

public class NoExpensesFoundException extends RuntimeException {
    public NoExpensesFoundException(String message) {
        super(message);
    }
}
