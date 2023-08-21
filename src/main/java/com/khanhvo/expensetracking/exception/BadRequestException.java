package com.khanhvo.expensetracking.exception;

import com.khanhvo.expensetracking.DTO.responseobject.ValidationError;

import java.util.List;

public class BadRequestException extends RuntimeException{
    private List<ValidationError> errorMessages;
    public BadRequestException(List<ValidationError> errorMessages) {
        super("Invalid request");
        this.errorMessages = errorMessages;
    }
    public List<ValidationError> getValidationErrors() {
        return errorMessages;
    }
}
