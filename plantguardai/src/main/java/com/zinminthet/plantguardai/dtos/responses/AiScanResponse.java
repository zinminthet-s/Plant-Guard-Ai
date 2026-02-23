package com.zinminthet.plantguardai.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiScanResponse {

    private ScanResponse result;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiseaseAndProbability{
        private String diseaseName;
        private String probability;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pesticide {
        private Long pesticideId;
        private String pesticideName;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScanResponse {
        private DiseaseAndProbability aiResult;
        private String prevention;
        private List<Pesticide> pesticides;

    }

}
