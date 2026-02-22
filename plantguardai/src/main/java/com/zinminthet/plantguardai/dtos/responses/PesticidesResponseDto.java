package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesticidesResponseDto {

    private Long id;

    private String name;

    private String info;

    private Double price;

    private String weight;

    private String ingredients;

    private String imagePath;
}
