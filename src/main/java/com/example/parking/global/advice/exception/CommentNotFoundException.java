package com.example.parking.global.advice.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {}

    public CommentNotFoundException(String message) {
        super(message);
    }
    public CommentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
