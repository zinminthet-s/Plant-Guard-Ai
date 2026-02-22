package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantShopsWithPesticidesResponseDto {

    private Merchant merchant;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Merchant {
        private Long merchantId;
        private String merchantName;
        private List<Shop> merchantShops;
    }

    @Data
    @AllArgsConstructor
    public static class Shop {
        private Long shopId;
        private String shopName;
        private String shopAddress;
        private List<Pesticide> Items;
    }

    @Data
    @AllArgsConstructor
    public static class Pesticide{
        private Long pesticideId;
        private String name;
        private Double price;
        private Photo photo;
    }

    @Data
    public static class Photo {
        private String url;
    }

}
