package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCreatedResponse {
    private Long shopId;
    private String shopName;
    private String shopAddress;
    private String description;
    private Merchant owner;

    @Data
    public static class Merchant {
        private String name;
    }

}
