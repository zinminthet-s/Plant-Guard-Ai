package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.entities.Otp;
import com.zinminthet.plantguardai.exceptions.ExpiredOtpCodeException;
import com.zinminthet.plantguardai.exceptions.InvalidOtpCodeException;
import jakarta.transaction.Transactional;

public interface OtpService{
    boolean validateOtpCode(String email, int otpCode) throws InvalidOtpCodeException, ExpiredOtpCodeException, Exception;

    @Transactional
    Otp associateOtpWithEmail(String email, int otpCode);

}
