package com.zinminthet.plantguardai.controllers;


import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.ChangePasswordRequest;
import com.zinminthet.plantguardai.dtos.requests.LoginRequestDto;
import com.zinminthet.plantguardai.dtos.responses.LoginResponseDto;
import com.zinminthet.plantguardai.services.AuthService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController  {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;


    @PostMapping("/api/auth/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) throws AuthenticationException {

        var responseDto = authService.login(loginRequestDto);
//        responseDto.setUserId();

        var apiResponse = ResponseBuilder.success(responseDto, String.format("%s login is successful.", responseDto.getRole()), request);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

     


    @PostMapping("/api/auth/changePassword")
    public ResponseEntity<ApiResponse<Object>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpServletRequest request) throws Exception {
        var isChanged =  authService.changePassword(changePasswordRequest);
        var response = ResponseBuilder.success(null, "Password Changed Successfully", request);
        return ResponseEntity.of(Optional.of(response));
    }

}
