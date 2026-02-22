package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.FarmerOrderPesticidesFromShopsRequestDto;
import com.zinminthet.plantguardai.services.OrderService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;




    @PostMapping("/api/farmer/{farmerId}/orders")
    public ResponseEntity<ApiResponse<Object>> farmerMakeOrder(@RequestBody FarmerOrderPesticidesFromShopsRequestDto requestDto, @PathVariable(name = "farmerId")Long farmerId, HttpServletRequest request){
         var saved = orderService.makeOrder(requestDto, farmerId);
         if(saved) {
             var apiResponse = ResponseBuilder.success(null, "Order is successfully created", request);
             return ResponseEntity.of(Optional.of(apiResponse));
         }else {
             var apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), "Order failed", request, null);
             return ResponseEntity.of(Optional.of(apiResponse));
         }

    }

}
