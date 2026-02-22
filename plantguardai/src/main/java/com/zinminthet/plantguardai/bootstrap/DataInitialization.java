package com.zinminthet.plantguardai.bootstrap;


import com.zinminthet.plantguardai.entities.*;
import com.zinminthet.plantguardai.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {

    private final MerchantRepository merchantRepository;
    private final PesticideRepository pesticideRepository;
    private final FarmerRepository farmerRepository;
    private final ShopRepository shopRepository;
    private final RoleRepository roleRepository;
    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        populateRole(List.of("admin", "farmer", "merchant"));
        populateFarmer("Kyaw Thu", "kyawthu022004@gmail.com", "kyawthu", "64th street");
        populateMerchant("Nara Kyaw Swar", "9/khamasa(N)/123123", "nrks@gmail.com", "nrks", List.of("Nara Limited", "Nara Family"));
        populateAdmin("Zin Min Thet", "zinminthet.software.engineer@gmail.com", "admin");
    }

    @Transactional
    public void populateRole(List<String> roles){

        var prefix = "ROLE_";
        if (!roles.isEmpty()){
            for(var role: roles) {
                var newRole = new Role();
                newRole.setName(prefix + role );
                newRole.setDescription("Role created by " + getClass().getSimpleName());
                roleRepository.save(newRole);
            }
        }
    }


    @Transactional
    public void populateAdmin(String name, String email, String password){
        var adminRole = roleRepository.findByName("ROLE_admin").get();

        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(passwordEncoder.encode(password));
        auth.setEmailVerified(true);
        auth.setRole(adminRole);

        authRepository.save(auth);

        var admin = new Admin();
        admin.setId(auth.getId());
        admin.setName(name);
        admin.setAuth(auth);

        adminRepository.save(admin);
    }


    @Transactional
    public void populateFarmer(String name, String email, String password, String address){
        var farmerRole = roleRepository.findByName("ROLE_farmer").get();
        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(String.format("{noop}%s", password));
        auth.setEmailVerified(true);
        auth.setRole(farmerRole);

        authRepository.save(auth);

        var farmer = new Farmer();
        farmer.setId(auth.getId());
        farmer.setAddress(address);
        farmer.setName(name);
        farmer.setAuth(auth);

        farmerRepository.save(farmer);

    }


    @Transactional
    public void populateMerchant(String name, String nrc, String email, String password, List<String> shopNames){
        var farmerRole = roleRepository.findByName("ROLE_merchant").get();
        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(String.format("{noop}%s", password));
        auth.setEmailVerified(true);
        auth.setRole(farmerRole);

        authRepository.save(auth);

        var merchant = new Merchant();
        merchant.setId(auth.getId());
        merchant.setName(name);
        merchant.setName(nrc);

        merchantRepository.save(merchant);

        if(!shopNames.isEmpty()){
            for(var shopName: shopNames) {
                var shop = new Shop();
                shop.setMerchant(merchant);
                shop.setName(shopName);
                shop.setDescription("Owned by" + merchant.getName());
                shop.setAddress("default street");

                shopRepository.save(shop);
            }
        }


    }


}
