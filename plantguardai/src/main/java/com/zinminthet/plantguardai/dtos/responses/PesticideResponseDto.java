package com.zinminthet.plantguardai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesticideResponseDto {

    private Long pesticideId;
    private String name;
    private Double price;
    private Photo photo;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Photo {
        private String url;
    }

}
