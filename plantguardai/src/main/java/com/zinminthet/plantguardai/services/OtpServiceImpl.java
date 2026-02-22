package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.entities.Otp;
import com.zinminthet.plantguardai.exceptions.ExpiredOtpCodeException;
import com.zinminthet.plantguardai.exceptions.InvalidOtpCodeException;
import com.zinminthet.plantguardai.repositories.OtpRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.zinminthet.plantguardai.utils.OtpUtils.checkIfExpired;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    @Override
    public boolean validateOtpCode(String email, int otpCode) throws InvalidOtpCodeException, ExpiredOtpCodeException, Exception{
        var otp = otpRepository.findByEmailAndCode(email, otpCode).orElseThrow(()->{
            throw new InvalidOtpCodeException("Otp code is invalid");
        });

        if(!(otpCode == otp.getCode())){
            throw new InvalidOtpCodeException("Wrong Otp code");
        }else {
            if(checkIfExpired(otp)){
                throw new ExpiredOtpCodeException("Otp code is expired");
            }else {
                otpRepository.delete(otp);
                return true;
            }
        }
    }

    @Override
    @Transactional
    public Otp associateOtpWithEmail(String email, int otpCode){

        otpRepository.deleteAllByEmail(email);

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime issuedTime = now;
        LocalDateTime expiredTime = now.plusSeconds(180); // 3 minutes

        Otp otp = Otp.builder()
                .code(otpCode)
                .email(email)
                .issuedAt(issuedTime)
                .expiredAt(expiredTime)
                .build();

        otpRepository.save(otp);

        return otp;

    }

}

