package com.zinminthet.plantguardai.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerOrderPesticidesFromShopsRequestDto {
    private String address;
    private List<Order> orders;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        private Long shopId;
        private List<Pesticide> Items;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shop{
        private Long shopId;
        private List<Pesticide> Items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pesticide {
        private Long pesticideId;
        private Long quantity;
    }


}



