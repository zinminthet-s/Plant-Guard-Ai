package com.zinminthet.plantguardai.exceptions;

public class ExpiredOtpCodeException extends RuntimeException {
    public ExpiredOtpCodeException(String message) {
        super(message);
    }
}
