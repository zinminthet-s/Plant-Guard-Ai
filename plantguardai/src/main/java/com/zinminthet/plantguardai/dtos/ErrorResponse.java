package com.zinminthet.plantguardai.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String field;
    private String message;
    private @Nullable String code;
}
