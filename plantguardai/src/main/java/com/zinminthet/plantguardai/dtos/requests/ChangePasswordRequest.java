package com.zinminthet.plantguardai.dtos.requests;


import lombok.Data;

@Data
public class ChangePasswordRequest {
    private Long userId;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
