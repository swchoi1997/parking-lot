package com.example.parking.global.advice.exception;

public class BuildingAlreadyExistsException extends RuntimeException {
    public BuildingAlreadyExistsException() {
    }

    public BuildingAlreadyExistsException(String message) {
        super(message);
    }

    public BuildingAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
