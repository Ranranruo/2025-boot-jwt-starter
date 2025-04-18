package com.example.demo.Auth.Exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException() {
        super("USERNAME_ALREADY_EXISTS");
    }
}
