package com.zinminthet.plantguardai.services;

import com.zinminthet.plantguardai.dtos.ApiResponse;
import com.zinminthet.plantguardai.dtos.requests.ChangePasswordRequest;
import com.zinminthet.plantguardai.dtos.requests.LoginRequestDto;
import com.zinminthet.plantguardai.dtos.responses.LoginResponseDto;
import com.zinminthet.plantguardai.exceptions.PasswordNotMached;
import com.zinminthet.plantguardai.repositories.AuthRepository;
import com.zinminthet.plantguardai.utils.JwtUtils;
import com.zinminthet.plantguardai.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handleAuthenticationRelatedExceptions(AuthenticationException e, HttpServletRequest request) {
        var apiResponse = ResponseBuilder.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws AuthenticationException {
        var email= loginRequestDto.getEmail();
        var password = loginRequestDto.getPassword();

        var auth = authRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email is not registered"));

        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        var authenticationResponse = authenticationManager.authenticate(authenticationToken);

        String jwt = null;

        String commaSeparatedRoles = authenticationResponse
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        if(authenticationResponse.isAuthenticated()){
            jwt = JwtUtils.generate(authenticationResponse.getName(), commaSeparatedRoles, auth.getId() );
        }else {
            throw new BadCredentialsException("Invalid credentials");
        }


        return LoginResponseDto.builder()
                .token(jwt)
                .userId(auth.getId())
                .email(email)
                .role(auth.getRole().getName().split("ROLE_")[1])
                .build();
    }

    @Transactional
    public boolean changePassword(ChangePasswordRequest request) throws PasswordNotMached, Exception{
        var userId = request.getUserId();
        var oldPassword = request.getOldPassword();
        var confirmPassword = request.getConfirmPassword();
        var newPassword = request.getNewPassword();

        try{
            var auth = authRepository.findById(userId).orElseThrow();
            if(passwordEncoder.matches(oldPassword, auth.getPassword())){
                if(newPassword.equals(confirmPassword)){
                    auth.setPassword(passwordEncoder.encode(newPassword));
                    authRepository.saveAndFlush(auth);
                }else {
                    throw new PasswordNotMached("Passwords do not match");
                }
            }else {
                throw new PasswordNotMached("Password is wrong");
            }

            return true;
        }
        catch (PasswordNotMached e){
            throw new PasswordNotMached(e.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e);
        }

    }

}
