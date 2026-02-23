package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.services.AiService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;
    private final String aiServiceApiUrl = "http://localhost:5000/api/ai/analyze";


    @PostMapping(value = "/api/ai/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void scanImageToFindOutDisease(@RequestPart("file")MultipartFile file, HttpServletRequest request) throws IOException {
        var response = aiService.sendImageToAiModelApi(file, aiServiceApiUrl);



    }
}
