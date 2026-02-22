package com.zinminthet.plantguardai.controllers;


import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.FarmerRegisterRequestDto;
import com.zinminthet.plantguardai.dtos.responses.FarmerOrderResponseDto;
import com.zinminthet.plantguardai.dtos.responses.FarmerProfileResponse;
import com.zinminthet.plantguardai.dtos.responses.PesticidesResponseDto;
import com.zinminthet.plantguardai.exceptions.FarmerNotFound;
import com.zinminthet.plantguardai.services.FarmerService;
import com.zinminthet.plantguardai.services.PesticideService;
import com.zinminthet.plantguardai.utils.ErrorMessage;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;
    private final PesticideService pesticideService;

    @ExceptionHandler(FarmerNotFound.class)
    public ResponseEntity<ErrorMessage> handleFarmerNotFound(FarmerNotFound e){
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleFarmerNotFound(Exception e){
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }


    @GetMapping("/api/farmers")
    public ResponseEntity<ApiResponse<List<FarmerProfileResponse>>> getAllFarmers(HttpServletRequest request){
            var response = farmerService.getAllFarmers();
            var apiResponse = ResponseBuilder.success(response, "All Farmer accounts are fetched", request);
            return ResponseEntity.of(Optional.of(apiResponse));
    }

    @PostMapping("/api/auth/register/farmer")
    public ResponseEntity<ApiResponse<Object>> registerFarmerAccount(@Valid @RequestBody FarmerRegisterRequestDto farmerRegisterRequestDto, HttpServletRequest request) {
        ApiResponse<Object> apiResponse = null;
        if( farmerService.createAccount(farmerRegisterRequestDto)){
            apiResponse = ResponseBuilder.success(null, "Farmer Account created successfully", request);
        }else {
            apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Account creation failed due to some reasons.", request, null);
        }
        return ResponseEntity.of(Optional.of(apiResponse));
    }


    @GetMapping("/api/auth/farmer/{farmerId}/me")
    public ResponseEntity<ApiResponse<FarmerProfileResponse>> getProfile(@PathVariable Long farmerId, HttpServletRequest request) throws FarmerNotFound, Exception {
        var profileResponse = farmerService.getProfile(farmerId);
        profileResponse.setUserId(farmerId);
        var response = ResponseBuilder.success(profileResponse, "Profile is fetched", request);
        return ResponseEntity.of(Optional.of(response));
    }


    @GetMapping("/api/farmer/{farmerId}/orders")
    public ResponseEntity<ApiResponse<List<FarmerOrderResponseDto>>> farmerOrderHistory(@PathVariable Long farmerId, HttpServletRequest request){
        List<FarmerOrderResponseDto> history = farmerService.fetchOrderHistoryByFarmerId(farmerId);
        var apiResponse = ResponseBuilder.success(history, String.format("Farmer with ID:%d's history get fetched", farmerId), request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }



//// serach bar query
//    POST: /api/pesticides/search?query=HIV
//            shops

    @PostMapping("/api/farmer/pesticides/search")
    public ResponseEntity<ApiResponse<List<PesticidesResponseDto>>> searchPesticides(@RequestParam(value = "query", required = true)String query, HttpServletRequest request){
        var response = pesticideService.searchPesticides(query);
        var apiResponse = ResponseBuilder.success(response, String.format("With `%s` query, pesticides are fetched", query), request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }

}
