package com.zinminthet.plantguardai.exceptions;

import javax.naming.AuthenticationException;

public class PhoneNumberNotFoundException extends AuthenticationException {
    public PhoneNumberNotFoundException(String message) {
        super(message);
    }
}
