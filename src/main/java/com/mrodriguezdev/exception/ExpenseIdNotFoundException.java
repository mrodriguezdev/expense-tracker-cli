package com.mrodriguezdev.exception;

public class ExpenseIdNotFoundException extends RuntimeException {
    public ExpenseIdNotFoundException(String message) {
        super(message);
    }
}
