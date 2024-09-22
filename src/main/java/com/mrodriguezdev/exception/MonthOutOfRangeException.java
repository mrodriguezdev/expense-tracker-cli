package com.mrodriguezdev.exception;

public class MonthOutOfRangeException extends IllegalArgumentException {
    public MonthOutOfRangeException(String s) {
        super(s);
    }
}
