package com.zinminthet.plantguardai.utils;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.ErrorResponse;
import com.zinminthet.plantguardai.dtos.Meta;
import com.zinminthet.plantguardai.dtos.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ResponseBuilder {
    public static <T> ApiResponse<T> success(T data, String message, HttpServletRequest request) {
        var path = request.getRequestURI();
        var method = request.getMethod();
        Meta meta = new Meta(UUID.randomUUID().toString(), path, Instant.now().toString(), method);
        return new ApiResponse<>("success", HttpStatus.OK.value(), message, data, null, meta, null);
    }

    public static <T> ApiResponse<T> successWithPagination(T data, String message, String path, String method, Pagination pagination) {
        Meta meta = new Meta(UUID.randomUUID().toString(), path, Instant.now().toString(), method);
        return new ApiResponse<>("success", HttpStatus.OK.value(), message, data, null, meta, pagination);
    }

    public static <T> ApiResponse<T> error(int code, String message, HttpServletRequest request, List<ErrorResponse> errors) {
        var path = request.getRequestURI();
        var method = request.getMethod();
        Meta meta = new Meta(UUID.randomUUID().toString(), path, Instant.now().toString(), method);
        return new ApiResponse<>("error", code, message, null, errors, meta, null);
    }

}
