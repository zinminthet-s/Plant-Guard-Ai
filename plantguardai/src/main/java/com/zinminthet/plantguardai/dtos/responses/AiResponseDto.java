package com.zinminthet.plantguardai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiResponseDto {
    private String diseaseName;
    private Double probability;
}
