package com.zinminthet.plantguardai.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SystemInfoFromAdminPovResponseDto {
    private Long farmerCounts;
    private Long merchantCounts;
    private Long advertisementCounts;
    private Long pesticideCounts;
}
