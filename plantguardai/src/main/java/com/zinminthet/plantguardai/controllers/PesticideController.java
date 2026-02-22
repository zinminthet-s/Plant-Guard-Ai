package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.AdminAddPesticideRequestDto;
import com.zinminthet.plantguardai.dtos.responses.PesticidesResponseDto;
import com.zinminthet.plantguardai.services.PesticideService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PesticideController {

    private final PesticideService pesticideService;



    private String name;

    private String info;

    private Double price;

    private String weight;

    private String ingredients;

    @PostMapping("/api/admin/pesticides")
    public ResponseEntity<ApiResponse<List<PesticidesResponseDto>>> addPesticide(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "info")String info,
            @RequestParam(name = "price")Double price,
            @RequestParam(name = "weight")String weight,
            @RequestParam(name = "ingredients")String ingredients,
            @RequestPart("file")MultipartFile image, HttpServletRequest request
    ) throws IOException {
        var adminAddPesticideRequestDto = new AdminAddPesticideRequestDto();
        adminAddPesticideRequestDto.setName(name);
        adminAddPesticideRequestDto.setInfo(info);
        adminAddPesticideRequestDto.setPrice(price);
        adminAddPesticideRequestDto.setWeight(weight);
        adminAddPesticideRequestDto.setIngredients(ingredients);
        var response = pesticideService.addPesticides(adminAddPesticideRequestDto, image);
        var apiResponse = ResponseBuilder.success(response, "Updated Pesticide List is fetched", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }

//    @PostMapping("/api/admin/pesticides")
//    public ResponseEntity<ApiResponse<List<PesticidesResponseDto>>> addPesticide(
//            @RequestPart("pesticide")AdminAddPesticideRequestDto adminAddPesticideRequestDto,
//            @RequestPart("file")MultipartFile image, HttpServletRequest request
//    ) throws IOException {
//        var response = pesticideService.addPesticides(adminAddPesticideRequestDto, image);
//        var apiResponse = ResponseBuilder.success(response, "Updated Pesticide List is fetched", request);
//        return ResponseEntity.of(Optional.of(apiResponse));
//    }



    @GetMapping("/api/pesticides")
    public ResponseEntity<ApiResponse<List<PesticidesResponseDto>>> getAllPesticides(HttpServletRequest request){
        var response = pesticideService.getAllPesticides();
        var apiResponse = ResponseBuilder.success(response, "Updated Pesticide List is fetched", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }


}
