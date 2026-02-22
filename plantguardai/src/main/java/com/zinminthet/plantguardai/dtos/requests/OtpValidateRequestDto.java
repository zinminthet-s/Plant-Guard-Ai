package com.zinminthet.plantguardai.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpValidateRequestDto {
    private String email;
    private String otpCode;
}
