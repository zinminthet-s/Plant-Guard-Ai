package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.responses.AiScanResponse;
import com.zinminthet.plantguardai.services.AiService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;
    private final String aiServiceApiUrl = "http://localhost:5000/api/ai/analyze";

    @PostMapping(value = "/api/ai/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<AiScanResponse>> scanImageToFindOutDisease(@RequestPart("file")MultipartFile file, HttpServletRequest request) throws IOException {
        var aiResponse = aiService.sendImageToAiModelApi(file, aiServiceApiUrl);
        var diseaseName = aiResponse.getDiseaseName();
        var diseaseProbability = aiResponse.getProbability();

        var response = aiService.searchCureAccordingToDisease(diseaseName, aiResponse);

        var apiResponse = ResponseBuilder.success(response, "AI has diagnosed disease successfully", request);

        return ResponseEntity.of(Optional.of(apiResponse));

    }



}
