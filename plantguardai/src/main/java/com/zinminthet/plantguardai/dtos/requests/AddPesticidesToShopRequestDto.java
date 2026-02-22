package com.zinminthet.plantguardai.dtos.requests;


//product  {
//
//    "merchantId": 44,
//            "pesticides": [
//    {
//        "pesticideId": 1,
//    },
//    {
//        "pesticideId": 12
//    }
//    ],
//    "shops": [
//    {
//        "shopId": 1,
//    }
//     ],
//
//
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPesticidesToShopRequestDto {
    private Long merchantId;
    private List<Pesticide> pesticides;
    private List<Shop> shops;

    @Data
    public static class Pesticide {
        private Long pesticideId;
    }

    @Data
    public static class Shop {
        private Long shopId;
    }
}