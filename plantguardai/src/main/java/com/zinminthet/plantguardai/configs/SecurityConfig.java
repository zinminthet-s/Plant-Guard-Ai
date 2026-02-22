package com.zinminthet.plantguardai.configs;

import com.zinminthet.plantguardai.auth.exceptionHandlers.AuthorizationFailureHandler;
import com.zinminthet.plantguardai.auth.providers.CustomUsernamePasswordAuthenticationProvider;
import com.zinminthet.plantguardai.auth.services.CustomUserDetailsService;
import com.zinminthet.plantguardai.filters.JwtFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        AuthenticationProvider authenticationProvider = new CustomUsernamePasswordAuthenticationProvider((CustomUserDetailsService) userDetailsService, passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http){

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(config-> {
            config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.exceptionHandling(config-> {
            config.accessDeniedHandler(new AuthorizationFailureHandler());
        });

        http.addFilterBefore(new JwtFilter(), BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/api/auth/login").permitAll();
            authorize.requestMatchers("/api/auth/changePassword").permitAll();
            authorize.requestMatchers("/api/merchant/**").permitAll();
            authorize.requestMatchers("/api/farmer/**").permitAll();
            authorize.anyRequest().permitAll();
        });

        http.cors(corsConfig-> {
            corsConfig.configurationSource(new CorsConfigurationSource() {
                @Override
                public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
                    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                    corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setMaxAge(3000000L);
                    return corsConfiguration;
                }
            });
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


//    @Bean
//    public AccessDeniedHandler customAccessDeniedHandler() { return (request, response, accessDeniedException) -> { response.setStatus(HttpStatus.FORBIDDEN.value()); response.setContentType("application/json"); response.getWriter().write("{\"error\":\"Access Denied: " + accessDeniedException.getMessage() + "\"}"); }; }

}
