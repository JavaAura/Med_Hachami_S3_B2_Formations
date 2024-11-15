package com.formations.exception.business;

public class DatabaseOperationException extends RuntimeException  {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
