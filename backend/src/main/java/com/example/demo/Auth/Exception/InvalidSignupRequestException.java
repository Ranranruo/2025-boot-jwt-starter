package com.example.demo.Auth.Exception;

public class InvalidSignupRequestException extends RuntimeException {
    public InvalidSignupRequestException(String field) {
        super("INVALID_"+field.toUpperCase());
    }
}
