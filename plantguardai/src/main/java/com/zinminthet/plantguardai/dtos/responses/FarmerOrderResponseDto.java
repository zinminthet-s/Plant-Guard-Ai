package com.zinminthet.plantguardai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerOrderResponseDto {

    private Long id;
    private String date;
    private Double totalAmount;
    private String deliveryAddress;
    private List<PesticideDto> items;
    private String shopName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PesticideDto {
        private Long pesticidesId;
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
