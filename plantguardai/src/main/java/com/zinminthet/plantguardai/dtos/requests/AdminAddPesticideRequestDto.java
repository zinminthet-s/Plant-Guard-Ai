package com.zinminthet.plantguardai.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAddPesticideRequestDto {

    private String name;

    private String info;

    private Double price;

    private String weight;

    private String ingredients;

}
