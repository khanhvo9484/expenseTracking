package com.khanhvo.expensetracking.auth.exception;

public class CustomBadCredentialsException extends CustomAuthenticationException{
    public CustomBadCredentialsException(String message) {
        super(message);
    }
}
