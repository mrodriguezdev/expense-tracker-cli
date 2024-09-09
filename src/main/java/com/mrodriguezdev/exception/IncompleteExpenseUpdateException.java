package com.mrodriguezdev.exception;

public class IncompleteExpenseUpdateException extends RuntimeException {
    public IncompleteExpenseUpdateException(String message) {
        super(message);
    }
}
