package com.zinminthet.plantguardai;

import com.zinminthet.plantguardai.utils.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
@EnableWebSecurity(debug = true)
public class PlantguardaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantguardaiApplication.class, args);
    }

}
