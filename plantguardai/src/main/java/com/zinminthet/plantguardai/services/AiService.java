package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.dtos.responses.AiResponseDto;
import com.zinminthet.plantguardai.dtos.responses.AiScanResponse;
import com.zinminthet.plantguardai.entities.Disease;
import com.zinminthet.plantguardai.repositories.CureRepository;
import com.zinminthet.plantguardai.repositories.DiseaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestTemplate restTemplate;
    private final CureRepository cureRepository;

    public AiResponseDto sendImageToAiModelApi(MultipartFile file, String apiLink) throws IOException {

        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Sent-From", "PGA organization");
        headers.setBearerAuth("thisisisomekindatokne");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        body.add("description", "The file is sent from pga organization");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);

         var response = restTemplate.postForObject(apiLink, httpEntity, AiResponseDto.class);
         return response;
    }


    public AiScanResponse searchCureAccordingToDisease(String diseaseName, AiResponseDto aiResponseDto){
        var cureEntity = cureRepository.findCureByDiseaseName(diseaseName);
        var responseDto = new AiScanResponse();
        var scanResponse = new AiScanResponse.ScanResponse();

        scanResponse.setPrevention(cureEntity.getPrevention());

        var probabilityInString = aiResponseDto.getProbability() * 100.0 + " percent";
        var diseaseAndProbability = new AiScanResponse.DiseaseAndProbability(aiResponseDto.getDiseaseName(), probabilityInString);
        scanResponse.setAiResult(diseaseAndProbability);

        var pesticides = cureEntity.getPesticides().stream()
                .map(pesticideEntity -> {
                    var pesticideDto = new AiScanResponse.Pesticide();
                    pesticideDto.setPesticideId(pesticideEntity.getId());
                    pesticideDto.setPesticideName(pesticideEntity.getName());
                    pesticideDto.setImagePath(pesticideEntity.getImagePath());


                    return pesticideDto;
                })
                .toList();
        scanResponse.setPesticides(pesticides);

        responseDto.setResult(scanResponse);

        return responseDto;
    }


}
