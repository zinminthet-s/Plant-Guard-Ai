package com.zinminthet.plantguardai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopsWithPesticidesResponseDto {

    private List<Shop> shops = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shop {
        private Long shopId;
        private String name;
        private List<Pesticide> Items;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pesticide{
        private Long pesticideId;
        private String name;
        private Double price;
        private Photo photo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String url;
    }

}
