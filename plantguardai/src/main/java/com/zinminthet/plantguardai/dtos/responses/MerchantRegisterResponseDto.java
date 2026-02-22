package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRegisterResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String nrc;
    private String role;
}
