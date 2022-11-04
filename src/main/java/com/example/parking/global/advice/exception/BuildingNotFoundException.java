package com.example.parking.global.advice.exception;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException() {
    }

    public BuildingNotFoundException(String message) {
        super(message);
    }

    public BuildingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
