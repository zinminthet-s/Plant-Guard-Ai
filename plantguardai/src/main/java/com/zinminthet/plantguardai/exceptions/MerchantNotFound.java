package com.zinminthet.plantguardai.exceptions;

public class MerchantNotFound extends RuntimeException {
    public MerchantNotFound(String message) {
        super(message);
    }

    public MerchantNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
