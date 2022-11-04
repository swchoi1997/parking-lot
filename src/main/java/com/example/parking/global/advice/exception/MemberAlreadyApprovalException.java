package com.example.parking.global.advice.exception;

public class MemberAlreadyApprovalException extends RuntimeException {
    public MemberAlreadyApprovalException() {}

    public MemberAlreadyApprovalException(String message) {
        super(message);
    }
    public MemberAlreadyApprovalException(String message, Throwable cause) {
        super(message, cause);
    }

}
