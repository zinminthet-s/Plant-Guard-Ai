package com.zinminthet.plantguardai.controllers;


import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.OtpRequestDto;
import com.zinminthet.plantguardai.dtos.requests.OtpValidateRequestDto;
import com.zinminthet.plantguardai.exceptions.ExpiredOtpCodeException;
import com.zinminthet.plantguardai.exceptions.InvalidOtpCodeException;
import com.zinminthet.plantguardai.repositories.AuthRepository;
import com.zinminthet.plantguardai.services.EmailService;
import com.zinminthet.plantguardai.services.OtpService;
import com.zinminthet.plantguardai.utils.EmailDetails;
import com.zinminthet.plantguardai.utils.OtpUtils;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/email")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;
    private final EmailService emailService;
    private final AuthRepository authRepository;

    @ExceptionHandler(InvalidOtpCodeException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidOtpCodeException(HttpServletRequest request, InvalidOtpCodeException e){

        var response = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Otp code is invalid", request, null);
        return ResponseEntity.of(Optional.of(response));
    }

    @ExceptionHandler(ExpiredOtpCodeException.class)
    public ResponseEntity<ApiResponse> handleExpiredOtpCodeException(HttpServletRequest request, ExpiredOtpCodeException e){

        var response = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Otp code is expired", request, null);
        return ResponseEntity.of(Optional.of(response));
    }


    @PostMapping("/otp/generate")
    public ResponseEntity<String> getOtpCode(@RequestBody OtpRequestDto otpRequestDto){
        int otpCode = OtpUtils.generateOtp();
        otpService.associateOtpWithEmail(otpRequestDto.getEmail(), otpCode);

        var emailDetails = new EmailDetails();
        emailDetails.setRecipient(otpRequestDto.getEmail());
        emailDetails.setSubject("OTP test");
        emailDetails.setMsgBody(String.valueOf(otpCode));

        emailService.sendSimpleMail(emailDetails);
        return ResponseEntity.status(HttpStatus.OK).body("Otp sent successfully");
    }

    @PostMapping("/otp/validate")
    public ResponseEntity<String> validateOtp(@RequestBody OtpValidateRequestDto otpValidateRequestDto){
        try {
                otpService.validateOtpCode(otpValidateRequestDto.getEmail(), Integer.parseInt(otpValidateRequestDto.getOtpCode()));


                var emailFound = authRepository.findByEmail(otpValidateRequestDto.getEmail()).orElseThrow(()->{
                    throw new UsernameNotFoundException("Email is not registered");
                });

                emailFound.setEmailVerified(true);

                authRepository.save(emailFound);

                return ResponseEntity.status(HttpStatus.OK).body("Otp confirmed");



        } catch (InvalidOtpCodeException e) {
            throw new RuntimeException(e);
        }
        catch (ExpiredOtpCodeException e){
            throw new RuntimeException(e);
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}

