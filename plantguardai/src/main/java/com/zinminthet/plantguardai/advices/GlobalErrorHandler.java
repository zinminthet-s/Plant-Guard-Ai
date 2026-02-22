package com.zinminthet.plantguardai.advices;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthenticationRelatedExceptions(AuthenticationException e, HttpServletRequest request){
        var apiResponse = ResponseBuilder.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception e, HttpServletRequest request){
        var apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

}
