package com.zinminthet.plantguardai.controllers;


import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.entities.Virus;
import com.zinminthet.plantguardai.repositories.VirusRepository;
import com.zinminthet.plantguardai.services.VirusService;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VirusController {

    private final VirusService virusService;
    private final VirusRepository virusRepository;

    public VirusController(VirusService virusService, VirusRepository virusRepository) {
        this.virusService = virusService;
        this.virusRepository = virusRepository;
    }

    @PostMapping("/viruses")
    public ResponseEntity<ApiResponse<Virus>> createVirus(@RequestBody() String name, HttpServletRequest request){
        var saved = virusService.addVirus(name);
        var apiResponse = ResponseBuilder.success(saved, "Virus added successfully", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }


    @GetMapping("/viruses")
    public ResponseEntity<List<Virus>> getAllViruses(){
        var viruses = virusRepository.findAll();
        return ResponseEntity.of(Optional.of(viruses));
    }

    @DeleteMapping("/viruses/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteVirusById(@PathVariable Long id, HttpServletRequest request){
        virusService.removeVirus(id);
        var apiResponse = ResponseBuilder.success(null, "Virus with " + id.toString() + " is removed", request);
        return ResponseEntity.of(Optional.of(apiResponse));
    }

}
