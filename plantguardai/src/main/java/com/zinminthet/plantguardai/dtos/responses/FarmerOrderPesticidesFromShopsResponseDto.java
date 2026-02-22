package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerOrderPesticidesFromShopsResponseDto {
    private String address;
    private Long farmerId;
    private List<Order> orders;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        private Long shopId;
        private String name;
        private List<Pesticide> Items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shop{
        private Long shopId;
        private String name;
        private List<Pesticide> Items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pesticide {
        private Long pesticideId;
        private String name;
        private Double price;
        private Long quantity;
        private Photo photo;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String url;
    }


}