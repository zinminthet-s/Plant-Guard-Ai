package com.zinminthet.plantguardai.dtos.requests;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerRegisterRequestDto {
    @NotBlank
    private String fullName;
    @Email
    private String email;
    @Nullable
    private String address;
    @NotBlank
    private String password;

    private @Nullable String role = "farmer";
}
