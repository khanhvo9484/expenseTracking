package com.khanhvo.expensetracking.exception;

public class DataConflictException extends RuntimeException{
    public DataConflictException(String message) {
        super(message);
    }
}
