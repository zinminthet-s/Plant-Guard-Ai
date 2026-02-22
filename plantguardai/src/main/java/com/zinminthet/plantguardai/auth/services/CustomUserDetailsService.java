package com.zinminthet.plantguardai.auth.services;

import com.zinminthet.plantguardai.repositories.AuthRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public CustomUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        var principal = authRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("Email is not registered")
        );
        var principalEmail = principal.getEmail();
        var password = principal.getPassword();
        var role = principal.getRole().getName().split("_")[1];

        return User.withUsername(principalEmail).password(password).roles(role).build();
    }


}
