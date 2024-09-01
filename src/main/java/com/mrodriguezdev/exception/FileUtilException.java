package com.mrodriguezdev.exception;

public class FileUtilException extends RuntimeException {
    public FileUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUtilException(String message) {
        super(message);
    }
}
