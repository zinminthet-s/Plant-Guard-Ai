package com.zinminthet.plantguardai.dtos.responses;

//{
//        "advertisements": [
//        {
//        "image": "uri"
//        },
//        {
//        "image": "uri"
//        }
//        ]
//        }


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementResponseDto {

    private List<Advertisement> advertisements;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Advertisement {
        private String image;
    }

}
