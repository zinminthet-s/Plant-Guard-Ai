package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerProfileResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String password = "placeholder";
    private Boolean isEmailVerified = false;
}