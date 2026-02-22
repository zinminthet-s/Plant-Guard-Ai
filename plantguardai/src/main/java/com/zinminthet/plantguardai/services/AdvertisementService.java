package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.responses.AdvertisementResponseDto;
import com.zinminthet.plantguardai.entities.Advertisement;
import com.zinminthet.plantguardai.repositories.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final FileStorageService fileStorageService;

    public AdvertisementResponseDto getAllAdvertisements(){
        var adEntities = advertisementRepository.findAll();
        List<AdvertisementResponseDto.Advertisement> dtos = new ArrayList<>();
        for(var ad: adEntities) {
            var dto = new AdvertisementResponseDto.Advertisement();
            dto.setImage(ad.getImagePath());
            dtos.add(dto);
        }
        var response = new AdvertisementResponseDto();
        response.setAdvertisements(dtos);

        return response;
    }

    public AdvertisementResponseDto addAds(Long adminId, List<MultipartFile> images) throws IOException {
        for(var image: images){
            var storedPath = fileStorageService.storeFile(image, "ads");
            Advertisement advertisement = new Advertisement();
            advertisement.setImagePath(storedPath);
            advertisementRepository.save(advertisement);
        }
        var adEntities = advertisementRepository.findAll();

        List<AdvertisementResponseDto.Advertisement> adsDto = new ArrayList<>();

        for(var adEntity: adEntities) {
            var adDto = new AdvertisementResponseDto.Advertisement();
            adDto.setImage(adEntity.getImagePath());
            adsDto.add(adDto);
        }

        var response = new AdvertisementResponseDto();

        response.setAdvertisements(adsDto);

        return response;


    }
}
