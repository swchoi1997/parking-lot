package com.example.parking.global.advice.exception;

public class BuildingNotRegisterException extends RuntimeException {
    public BuildingNotRegisterException() {}

    public BuildingNotRegisterException(String message) {
        super(message);
    }
    public BuildingNotRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

}
