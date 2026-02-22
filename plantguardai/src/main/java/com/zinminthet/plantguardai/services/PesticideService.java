package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.requests.AdminAddPesticideRequestDto;
import com.zinminthet.plantguardai.dtos.responses.PesticidesResponseDto;
import com.zinminthet.plantguardai.entities.Pesticide;
import com.zinminthet.plantguardai.repositories.PesticideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PesticideService {
    private final PesticideRepository pesticideRepository;
    private final FileStorageService fileStorageService;

    public List<PesticidesResponseDto> searchPesticides(String query) {
        var pesticideEntities = pesticideRepository.searchPesticideByQuery(query);


        var responseDto = pesticideEntities.stream().map(
                pesticide -> {
                    var response =  new PesticidesResponseDto();
                    response.setId(pesticide.getId());
                    response.setName(pesticide.getName());
                    response.setInfo(pesticide.getInfo());
                    response.setPrice(pesticide.getPrice());
                    response.setWeight(pesticide.getWeight());
                    response.setImagePath(pesticide.getImagePath());
                    response.setIngredients(pesticide.getIngredients());
                    return response;
                }
        ).toList();

        return responseDto;


    }

    public List<PesticidesResponseDto> addPesticides(AdminAddPesticideRequestDto adminAddPesticideRequestDto, MultipartFile image) throws IOException {
        var pesticideEntity = new Pesticide();
        pesticideEntity.setName(adminAddPesticideRequestDto.getName());
        pesticideEntity.setPrice(adminAddPesticideRequestDto.getPrice());
        pesticideEntity.setInfo(adminAddPesticideRequestDto.getInfo());
        pesticideEntity.setIngredients(adminAddPesticideRequestDto.getIngredients());
        pesticideEntity.setWeight(adminAddPesticideRequestDto.getWeight());


        var storedPath = fileStorageService.storeFile(image, "pesticides");


        var pesticideEntities = pesticideRepository.findAll();





        pesticideEntity.setImagePath(storedPath);

        pesticideRepository.save(pesticideEntity);

        List<PesticidesResponseDto> responsesDto = new ArrayList<>();

        var pesticidesEntities = pesticideRepository.findAll();

        for(var pesticide: pesticidesEntities){
            var dto = new PesticidesResponseDto();
            dto.setId(pesticide.getId());
            dto.setName(pesticide.getName());
            dto.setPrice(pesticide.getPrice());
            dto.setInfo(pesticide.getInfo());
            dto.setImagePath(pesticide.getImagePath());
            dto.setIngredients(pesticide.getIngredients());
            dto.setWeight(pesticide.getWeight());

            responsesDto.add(dto);
        }

        return responsesDto;




    }

    public List<PesticidesResponseDto> getAllPesticides() {
        List<PesticidesResponseDto> responsesDto = new ArrayList<>();

        var pesticidesEntities = pesticideRepository.findAll();

        for(var pesticide: pesticidesEntities){
            var dto = new PesticidesResponseDto();
            dto.setId(pesticide.getId());
            dto.setName(pesticide.getName());
            dto.setPrice(pesticide.getPrice());
            dto.setInfo(pesticide.getInfo());
            dto.setImagePath(pesticide.getImagePath());
            dto.setIngredients(pesticide.getIngredients());
            dto.setWeight(pesticide.getWeight());

            responsesDto.add(dto);
        }

        return responsesDto;
    }

}
