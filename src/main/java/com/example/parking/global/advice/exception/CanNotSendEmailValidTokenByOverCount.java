package com.example.parking.global.advice.exception;

public class CanNotSendEmailValidTokenByOverCount extends RuntimeException {
    public CanNotSendEmailValidTokenByOverCount() {
    }

    public CanNotSendEmailValidTokenByOverCount(String message) {
        super(message);
    }

    public CanNotSendEmailValidTokenByOverCount(String message, Throwable cause) {
        super(message, cause);
    }
}
