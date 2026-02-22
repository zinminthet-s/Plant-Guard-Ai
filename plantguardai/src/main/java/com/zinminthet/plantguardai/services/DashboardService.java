package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.responses.SystemInfoFromAdminPovResponseDto;
import com.zinminthet.plantguardai.repositories.AdvertisementRepository;
import com.zinminthet.plantguardai.repositories.FarmerRepository;
import com.zinminthet.plantguardai.repositories.MerchantRepository;
import com.zinminthet.plantguardai.repositories.PesticideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final FarmerRepository farmerRepository;
    private final MerchantRepository merchantRepository;
    private final PesticideRepository pesticideRepository;
    private final AdvertisementRepository advertisementRepository;

    public SystemInfoFromAdminPovResponseDto getSystemInfoFromAdminPov() {
        var farmerCount = farmerRepository.count();
        var merchantCount = merchantRepository.count();
        var pesticideCount = pesticideRepository.count();
        var adsCount = advertisementRepository.count();

        return SystemInfoFromAdminPovResponseDto.builder()
                .merchantCounts(merchantCount)
                .farmerCounts(farmerCount)
                .pesticideCounts(pesticideCount)
                .advertisementCounts(adsCount)
                .build();

    }
}
