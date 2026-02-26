package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.ErrorResponse;
import com.zinminthet.plantguardai.dtos.requests.AddPesticidesToShopRequestDto;
import com.zinminthet.plantguardai.dtos.requests.CreateShopRequest;
import com.zinminthet.plantguardai.dtos.requests.MerchantRegisterRequestDto;
import com.zinminthet.plantguardai.dtos.responses.*;
import com.zinminthet.plantguardai.dtos.responses.shopResponseDto.ShopResponseDto;
import com.zinminthet.plantguardai.exceptions.MerchantNotFound;
import com.zinminthet.plantguardai.services.MerchantService;
import com.zinminthet.plantguardai.utils.ErrorMessage;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @ExceptionHandler(MerchantNotFound.class)
    public ResponseEntity<ErrorMessage> handleMerchantNotFound(MerchantNotFound e){
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(  MethodArgumentNotValidException e, HttpServletRequest request){
        var fieldErrors = e.getBindingResult().getFieldErrors();

        List<ErrorResponse> errorResponses = new ArrayList<>();

        for(var fieldError: fieldErrors){
            var errorResponse = new ErrorResponse();
            errorResponse.setField(fieldError.getField());
            errorResponse.setMessage(fieldError.getDefaultMessage());
            errorResponse.setCode(fieldError.getCode());
            errorResponses.add(errorResponse);
        }

        var apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Form Fields error", request, errorResponses);

        return ResponseEntity.of(Optional.of(apiResponse));
    }





    @GetMapping("/api/merchant/{merchantId}/shops")
    public ResponseEntity<ApiResponse<List<ShopResponseDto>>> getAllShopsByMerchantId(
            @PathVariable("merchantId")Long merchantId,
            HttpServletRequest request
    ){
        var response = merchantService.getAllShopsById(merchantId);
        var apiResponse = ResponseBuilder.success(response, String.format("All Shops owned by Merchant Id: %d is fetched", merchantId), request);

        return ResponseEntity.of(Optional.of(apiResponse));
    }

    @PostMapping("/api/merchant/{merchantId}/shops")
    public ResponseEntity<ApiResponse<List<ShopCreatedResponse>>> createShop(@PathVariable Long merchantId, @RequestBody CreateShopRequest createShopRequest, HttpServletRequest request) throws AuthorizationDeniedException,Exception {
        var createdResponse = merchantService.createShop(merchantId, createShopRequest);
        var apiResponse = ResponseBuilder.success(createdResponse, "Shop is created", request);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/api/auth/register/merchant")
    public ResponseEntity<ApiResponse<Object>> merchantRegister(@Valid @RequestBody MerchantRegisterRequestDto merchantRegisterRequestDto, HttpServletRequest request) {
        ApiResponse<Object> apiResponse = null;
        var saved = merchantService.createAccount(merchantRegisterRequestDto);
        if(Objects.nonNull(saved)){
            apiResponse = ResponseBuilder.success(saved, "Merchant Account created successfully", request);
        }else {
            apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Account creation failed due to some reasons.", request, null);
        }
        return ResponseEntity.of(Optional.of(apiResponse));

    }

//    tested
//    success
    @GetMapping("/api/merchant/{merchantId}/me")
    public ResponseEntity<ApiResponse<MerchantProfileResponse>> getProfile(@PathVariable Long merchantId, HttpServletRequest request) throws Exception {
        var apiResponse = merchantService.getProfile(merchantId);
        var response = ResponseBuilder.success(apiResponse, String.format("Merchant Id: %d's profile is fetched", merchantId), request);
        return ResponseEntity.of(Optional.of(response));
    }


    @GetMapping("/api/shops")
    public ResponseEntity<ApiResponse<ShopsWithPesticidesResponseDto>> getAllShopsAndItsPesticides(HttpServletRequest request){
        var response = merchantService.getAllShopWithItsPesticides();
        var apiResponse = ResponseBuilder.success(response, "Fetched shops with its pesticides", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }


    @GetMapping("/api/merchant/{merchantId}/shops/pesticides")
    public ResponseEntity<ApiResponse<List<PesticideResponseDto>>> getPesticidesByMerchantId(@PathVariable Long merchantId, HttpServletRequest request){

        var response = merchantService.getPesticidesByMerchantId(merchantId);
        var apiResponse = ResponseBuilder.success(response, "Pesticdes are fetched", request);
        return ResponseEntity.of(Optional.of(apiResponse));

    }

    @GetMapping("/api/merchant/{merchantId}/shops/{shopId}/pesticides")
    public ResponseEntity<ApiResponse<ShopWithPesticidesResponseDto>> getPesticidesByShopId(@PathVariable Long merchantId ,@PathVariable Long shopId, HttpServletRequest request){
            var response = merchantService.getPesticidesByShopId(merchantId, shopId);
            var apiResponse = ResponseBuilder.success(response, String.format("Shop's id: %d pesticides are fetched", shopId), request);
            return ResponseEntity.of(Optional.of(apiResponse));
    }



    @GetMapping("/api/merchant/{merchantId}/system")
    public ResponseEntity<ApiResponse<SystemInfoFromMerchantPovResponseDto>> getSystemInfoFromMerchantPov(@PathVariable(name = "merchantId")Long merchantId, HttpServletRequest request){
        var response = merchantService.getSystemInfoFromMerchantPov(merchantId);
        var apiResponse = ResponseBuilder.success(
                response, "Fetched merchant's shops with its pesticides", request
        );
        return ResponseEntity.of(Optional.of(apiResponse));
    }


//    tested
//    success
    @PostMapping("/api/merchant/{merchantId}/shops/pesticides")
    public ResponseEntity<ApiResponse<MerchantShopsWithPesticidesResponseDto>> addPesticides(@RequestBody AddPesticidesToShopRequestDto requestDto, @PathVariable Long merchantId, HttpServletRequest request){
        var response = merchantService.addPesticidesToShops(requestDto);

        var apiResponse = ResponseBuilder.success(
                response, "Fetched merchant's shops with its pesticides", request
        );
        return ResponseEntity.of(Optional.of(apiResponse));
    }




//    not tested
    @GetMapping("/api/merchant/{merchantId}/orders")
    public ResponseEntity<ApiResponse<List<MerchantOrderResponseDto>>> getMerchantOrder(@PathVariable(name = "merchantId")Long merchantId ,HttpServletRequest request){
        var response = merchantService.getMerchantOrdersById(merchantId);
        var apiResponse = ResponseBuilder.success(response, "Fetched merchant's orders", request);
        return ResponseEntity.of(Optional.of(apiResponse));

    }

}
