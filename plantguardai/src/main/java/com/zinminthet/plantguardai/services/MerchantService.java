package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.requests.AddPesticidesToShopRequestDto;
import com.zinminthet.plantguardai.dtos.requests.CreateShopRequest;
import com.zinminthet.plantguardai.dtos.requests.MerchantRegisterRequestDto;
import com.zinminthet.plantguardai.dtos.responses.*;
import com.zinminthet.plantguardai.dtos.responses.shopResponseDto.ShopResponseDto;
import com.zinminthet.plantguardai.entities.*;
import com.zinminthet.plantguardai.exceptions.MerchantNotFound;
import com.zinminthet.plantguardai.exceptions.PesticideNotFound;
import com.zinminthet.plantguardai.exceptions.RoleNotExists;
import com.zinminthet.plantguardai.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;
    private final ShopRepository shopRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final PesticideRepository pesticideRepository;

    private String name;
    private String email;
    private String password;
    private String nrc;
    private String role = "merchant";

    public MerchantShopsWithPesticidesResponseDto addPesticidesToShops(AddPesticidesToShopRequestDto requestDto) throws MerchantNotFound{

        List<AddPesticidesToShopRequestDto.Shop> shops = requestDto.getShops();
        List<AddPesticidesToShopRequestDto.Pesticide> pesticides = requestDto.getPesticides();
        var merchantId = requestDto.getMerchantId();

        List<Pesticide> pesticideEntities = new ArrayList<>();

        var merchantEntity = merchantRepository.findById(merchantId).orElseThrow(
                ()-> new MerchantNotFound(String.format("Merchant with id: %d does not exists", merchantId))
        );

        var shopEntities = merchantEntity.getShops();

        for(var pesticide: pesticides){
            var pesticideEntity = pesticideRepository.findById(pesticide.getPesticideId()).orElseThrow(
                    ()->{
                        throw new PesticideNotFound(String.format("Pesticide with %d does not exist", pesticide.getPesticideId()));
                    }
            );
            pesticideEntities.add(pesticideEntity);
        }

        var shopDtos = requestDto.getShops();

        List<Shop> requiredShopEntities = new ArrayList<>();

        for(var dto: shopDtos){
            var entity = shopRepository.findById(dto.getShopId()).orElseThrow();
            requiredShopEntities.add(entity);
        }

        merchantEntity.addPesticidesToShop(requiredShopEntities, pesticideEntities);


        var savedMerchant = merchantRepository.save(merchantEntity);
        List<MerchantShopsWithPesticidesResponseDto.Shop> shopsDto = new ArrayList<>();

        for(var shop: savedMerchant.getShops()){
            List<MerchantShopsWithPesticidesResponseDto.Pesticide> ItemsDto = new ArrayList<>();


            var shopPesticides= shop.getPesticides();


            //map pesticide entity to dto pesticide
            //return PesticideDto
            for(var pesticide: shopPesticides){
                var photoDto = new MerchantShopsWithPesticidesResponseDto.Photo();
                photoDto.setUrl(pesticide.getImagePath());
                var dto = new MerchantShopsWithPesticidesResponseDto.Pesticide(
                        pesticide.getId(),
                        pesticide.getName(),
                        pesticide.getPrice(),
                        photoDto
                );

                ItemsDto.add(dto);
            }

            var shopDto = new MerchantShopsWithPesticidesResponseDto.Shop(
                    shop.getId(),
                    shop.getName(),
                    shop.getAddress(),
                    ItemsDto
            );

            shopsDto.add(shopDto);
        }

        var merchantDto = new MerchantShopsWithPesticidesResponseDto.Merchant(
                savedMerchant.getId(),
                savedMerchant.getName(),
                shopsDto
        );


        var response = new MerchantShopsWithPesticidesResponseDto();
        response.setMerchant(merchantDto);

        return response;

    }


    @Transactional
    public MerchantRegisterResponseDto createAccount(MerchantRegisterRequestDto merchantRegisterRequestDto){
        try {

            String name = merchantRegisterRequestDto.getFullName();
            String email = merchantRegisterRequestDto.getEmail();
            String password = merchantRegisterRequestDto.getPassword();
            String nrc = merchantRegisterRequestDto.getNrc();

            Merchant merchant = new Merchant();
            merchant.setName(name);
            merchant.setNrc(nrc);

            Auth auth = new Auth();

            Role roleFound = roleRepository.findByName("ROLE_" + role).orElseThrow(()-> new RoleNotExists(String.format("%s does not exists", role)));


            auth.setRole(roleFound);
            auth.setEmail(email);
            auth.setPassword(passwordEncoder.encode(password));

            merchant.setAuth(auth);

            var savedAuth = authRepository.save(auth);
            merchant.setId(savedAuth.getId());

            var merchantEntity = merchantRepository.save(merchant);
            var response = new MerchantRegisterResponseDto();
            response.setId(merchantEntity.getId());
            response.setNrc(merchantEntity.getNrc());
            response.setRole(merchantEntity.getAuth().getRole().getName().split("ROLE_")[1]);
            response.setEmail(merchantEntity.getAuth().getEmail());
            response.setFullName(merchantEntity.getName());


            return response;



        } catch (RoleNotExists e) {
            throw new RuntimeException(e);
        }
    }

    public MerchantProfileResponse getProfile(Long id) throws MerchantNotFound, Exception{
        var merchant = merchantRepository.findById(id).orElseThrow(
                ()-> new MerchantNotFound(String.format("Merchant with %d is not found.", id))
        );

        var auth= merchant.getAuth();
        String email = auth.getEmail();
        var isEmailVerified = auth.getEmailVerified();


        var response = new MerchantProfileResponse();
        response.setEmail(email);
        response.setFullName(merchant.getName());
        response.setNrc(merchant.getNrc());

        return response;

    }


//    @PostMapping("/api/merchant/{merchantId}/shops")
//    public void createShop(@PathVariable Long merchantId){
//
//    }

    @Transactional
    public List<ShopCreatedResponse> createShop(Long merchantId, CreateShopRequest createShopRequest) throws MerchantNotFound, Exception {
        var shopName = createShopRequest.getShopName();
        var merchantInRequest = createShopRequest.getMerchantId();

        if(!merchantInRequest.equals(merchantId)){
            throw new MerchantNotFound("Merchant IDs do not match");
        }

        var shopAddress = createShopRequest.getShopAddress();
        var shopDescription = createShopRequest.getDescription();

        var merchant = merchantRepository.findById(merchantId).orElseThrow(
                ()-> new MerchantNotFound(String.format("Merchant with id: %d is not found.", merchantId))
        );

        var shopEntity = new Shop();
        shopEntity.setName(shopName);
        shopEntity.setAddress(shopAddress);
        shopEntity.setDescription(shopDescription);

        merchant.addShop(shopEntity);

        var savedShop = shopRepository.save(shopEntity);
        var merchantEntity = merchantRepository.save(merchant);


        var shops = merchantEntity.getShops();

        List<ShopCreatedResponse> responses = new ArrayList<>();

        for(var shop: shops){
            var dto = new ShopCreatedResponse();

            var owner = new ShopCreatedResponse.Merchant();
            owner.setName(merchantEntity.getName());

            dto.setOwner(owner);
            dto.setShopId(shop.getId());
            dto.setDescription(shop.getDescription());
            dto.setShopName(shop.getName());
            dto.setShopAddress(shop.getAddress());

            responses.add(dto);
        }

        return responses;

    }

    public List<ShopResponseDto> getAllShopsById(Long merchantId) {
        var merchantEntity = merchantRepository.findById(merchantId).orElseThrow(
                ()-> {
                    throw new MerchantNotFound(String.format("Merchant with %d does not exists", merchantId));
                }
        );

        var shops = merchantEntity.getShops();

        List<ShopResponseDto> responsesDto = new ArrayList<>();

        for(var shop: shops) {
            var response = new ShopResponseDto();
            response.setShopName(shop.getName());
            response.setDescription(shop.getDescription());
            response.setShopAddress(shop.getAddress());
            response.setId(shop.getId());

            responsesDto.add(response);
        }

        return responsesDto;

    }

    public ShopsWithPesticidesResponseDto getAllShopWithItsPesticides() {
        var shopEntities = shopRepository.findAll();

        var response = new ShopsWithPesticidesResponseDto();

        for(var shopEntity: shopEntities){
                var shopDto = new ShopsWithPesticidesResponseDto.Shop();
                shopDto.setShopId(shopEntity.getId());
                shopDto.setName(shopEntity.getName());
                var pesticideEntities = shopEntity.getPesticides();
                var pesticidesDto = pesticideEntities.stream()
                        .map(pesticide -> {
                            var dto =  new ShopsWithPesticidesResponseDto.Pesticide();
                            dto.setPesticideId(pesticide.getId());
                            dto.setPrice(pesticide.getPrice());
                            dto.setPhoto(new ShopsWithPesticidesResponseDto.Photo(pesticide.getImagePath()));
                            dto.setName(pesticide.getName());
                            return dto;
                        })
                        .toList();


                shopDto.setItems(pesticidesDto);

            response.getShops().add(shopDto);
            }

        return response;




    }

    public SystemInfoFromMerchantPovResponseDto getSystemInfoFromMerchantPov(Long merchantId) {
        var merchant = merchantRepository.findById(merchantId).get();
        var shopCount = merchant.getShops().stream().count();
        var response = new SystemInfoFromMerchantPovResponseDto();

        var shopEntites = merchant.getShops();

        var pesticideCount = 0L;

        for(var shopEntity: shopEntites) {
            var countPesticideEachShop = shopEntity.getPesticides().stream().count();
            pesticideCount += countPesticideEachShop;
        }

        response.setShopCounts(shopCount);
        response.setPesticideCounts(pesticideCount);

        return response;

    }
}
