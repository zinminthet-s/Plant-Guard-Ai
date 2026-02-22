package com.zinminthet.plantguardai.controllers;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.responses.SystemInfoFromAdminPovResponseDto;
import com.zinminthet.plantguardai.services.DashboardService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;


    @GetMapping("/api/admin/system")
    public ResponseEntity<ApiResponse<SystemInfoFromAdminPovResponseDto>> getSystemInfoFromAdminPov(HttpServletRequest request){
        var response = dashboardService.getSystemInfoFromAdminPov();
        var apiResponse = ResponseBuilder.success(response, "System info are fetched from admin pov", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }


}
