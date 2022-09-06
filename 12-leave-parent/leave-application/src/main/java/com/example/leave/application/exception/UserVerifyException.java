package com.example.leave.application.exception;

public class UserVerifyException extends Exception{

    public UserVerifyException(String username) {
        super(String.format("username:%s verify err",username));
    }
}
