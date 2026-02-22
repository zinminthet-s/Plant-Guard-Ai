package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.FarmerRegisterRequestDto;
import com.zinminthet.plantguardai.dtos.responses.FarmerOrderResponseDto;
import com.zinminthet.plantguardai.dtos.responses.FarmerProfileResponse;
import com.zinminthet.plantguardai.entities.Auth;
import com.zinminthet.plantguardai.entities.Farmer;
import com.zinminthet.plantguardai.entities.Role;
import com.zinminthet.plantguardai.exceptions.FarmerNotFound;
import com.zinminthet.plantguardai.exceptions.RoleNotExists;
import com.zinminthet.plantguardai.exceptions.UserAlreadyExists;
import com.zinminthet.plantguardai.repositories.AuthRepository;
import com.zinminthet.plantguardai.repositories.FarmerRepository;
import com.zinminthet.plantguardai.repositories.RoleRepository;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmerService {
    private final FarmerRepository farmerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExistsException(UserAlreadyExists e, HttpServletRequest request){
        var apiResponse = ResponseBuilder.error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request, null);
        return ResponseEntity.of(Optional.of(apiResponse));
    }




    @Transactional
    public boolean createAccount(FarmerRegisterRequestDto farmerRegisterRequestDto){
        try {
            String name = farmerRegisterRequestDto.getFullName();
            String email = farmerRegisterRequestDto.getEmail();
            String password = farmerRegisterRequestDto.getPassword();
            String address = farmerRegisterRequestDto.getAddress();
            String role = farmerRegisterRequestDto.getRole();


            var exist = authRepository.findByEmail(email).orElse(null);

            if (!Objects.isNull(exist)) {
                throw new UserAlreadyExists(String.format("User with email: %s already exists", exist.getEmail()));
            }

            var farmer = new Farmer();
            farmer.setName(name);
            farmer.setAddress(address);

            Auth auth = new Auth();

            Role roleFound = roleRepository.findByName("ROLE_" + role).orElseThrow(()-> new RoleNotExists(String.format("%s does not exists", role)));

            auth.setRole(roleFound);
            auth.setEmail(email);
            auth.setPassword(passwordEncoder.encode(password));

            farmer.setAuth(auth);

            var savedAuth = authRepository.save(auth);
            farmer.setId(savedAuth.getId());
            farmerRepository.save(farmer);

            return true;

        } catch (RoleNotExists e) {
            throw new RuntimeException(e);
        }
    }

    public FarmerProfileResponse getProfile(Long id) throws FarmerNotFound{
        var farmer = farmerRepository.findById(id).orElseThrow(
                ()-> new FarmerNotFound(String.format("Farmer with %d is not found.", id))
        );

        var auth= farmer.getAuth();
        String email = auth.getEmail();
        var isEmailVerified = auth.getEmailVerified();

        var response = new FarmerProfileResponse();
        response.setFullName(farmer.getName());
        response.setEmail(email);
        response.setIsEmailVerified(isEmailVerified);

        return response;

    }

    public List<FarmerOrderResponseDto> fetchOrderHistoryByFarmerId(Long farmerId){
        var farmerEntity = farmerRepository.findById(farmerId).orElseThrow(
                () -> {
                    throw new FarmerNotFound(String.format("Farmer with %d is not found", farmerId));
                }
        );

        List<FarmerOrderResponseDto> responses = new ArrayList<>();

        var orderEntities = farmerEntity.getOrders();


        for(var orderEntity: orderEntities){
            var shopEntity = orderEntity.getShop();
            var orderLineEntities = orderEntity.getOrderLines();
            for(var orderLineEntity: orderLineEntities) {
                var pesticideEntities = orderLineEntity.getPesticides();

                var pesticidesDto = pesticideEntities.stream()
                        .map((pesticideEntity)->{
                                var pesticideDto = new FarmerOrderResponseDto.PesticideDto();
                                pesticideDto.setPesticidesId(pesticideEntity.getId());
                                pesticideDto.setPhoto(new FarmerOrderResponseDto.Photo(pesticideEntity.getImagePath()));
                                pesticideDto.setQuantity(Long.valueOf(orderLineEntity.getQuantity()));
                                pesticideDto.setPrice(pesticideEntity.getPrice());
                                pesticideDto.setName(pesticideEntity.getName());

                            return pesticideDto;
                        })
                        .toList();

                var response = new FarmerOrderResponseDto();
                response.setId(orderEntity.getId());
                response.setDate(LocalDate.now().toString());
                response.setTotalAmount(orderEntity.getTotalCost());
                response.setItems(pesticidesDto);
                response.setShopName(shopEntity.getName());
                response.setDeliveryAddress(orderEntity.getAddress());

                responses.add(response);
            }
        }

        return responses;
    }

    public List<FarmerProfileResponse> getAllFarmers() {
        var farmerEntities = farmerRepository.findAll();

        var responses =  farmerEntities.stream()
                .map(farmerEntity -> {
                    var response = new FarmerProfileResponse();
                    response.setUserId(farmerEntity.getId());
                    response.setPassword(null);
                    response.setFullName(farmerEntity.getName());
                    response.setEmail(farmerEntity.getAuth().getEmail());
                    response.setIsEmailVerified(farmerEntity.getAuth().getEmailVerified());
                    return response;
                })
                .toList();


        return responses;

    }
}
