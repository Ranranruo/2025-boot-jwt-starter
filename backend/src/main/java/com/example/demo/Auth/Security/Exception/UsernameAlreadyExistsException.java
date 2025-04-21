package com.example.demo.Auth.Security.Exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException() {
        super("USERNAME_ALREADY_EXISTS");
    }
}
