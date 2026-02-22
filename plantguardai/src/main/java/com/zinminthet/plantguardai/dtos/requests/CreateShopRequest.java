package com.zinminthet.plantguardai.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShopRequest {
    private Long merchantId;
    private String shopName;
    private String shopAddress;
    private String description;
}