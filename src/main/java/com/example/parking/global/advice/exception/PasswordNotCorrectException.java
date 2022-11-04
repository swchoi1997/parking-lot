package com.example.parking.global.advice.exception;

public class PasswordNotCorrectException extends RuntimeException {
    public PasswordNotCorrectException() {}

    public PasswordNotCorrectException(String message) {
        super(message);
    }
    public PasswordNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

}
