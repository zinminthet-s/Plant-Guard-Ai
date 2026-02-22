package com.zinminthet.plantguardai.dtos;


import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String statusText;
    private int statusCode;
    private String message;
    private T data;
    private List<ErrorResponse> errors;
    private Meta meta;
    private Pagination pagination;
}
