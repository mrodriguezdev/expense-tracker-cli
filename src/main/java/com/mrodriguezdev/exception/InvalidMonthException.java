package com.mrodriguezdev.exception;

public class InvalidMonthException extends IllegalArgumentException {
    public InvalidMonthException(String s) {
        super(s);
    }
}
