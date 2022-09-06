package com.example.leave.application.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String username) {
        super(String.format("user:%s not found",username));
    }
}
