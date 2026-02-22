package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.responses.AdvertisementResponseDto;
import com.zinminthet.plantguardai.services.AdvertisementService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @GetMapping("/api/ads")
    public ResponseEntity<ApiResponse<AdvertisementResponseDto>> getAllAds(HttpServletRequest request){
        var response = advertisementService.getAllAdvertisements();
        var apiResponse = ResponseBuilder.success(response, "Fetched All ads", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }

    @PostMapping(value = "/api/admin/ads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<AdvertisementResponseDto>> addAd(
            @RequestParam("adminId") Long adminId,
            @RequestPart("images")List<MultipartFile> images,
            HttpServletRequest request
            ) throws IOException {
                var response = advertisementService.addAds(adminId, images);
                var apiResponse = ResponseBuilder.success(response, "Ad is successfully added", request);
                return ResponseEntity.of(Optional.of(apiResponse));
    }

}
