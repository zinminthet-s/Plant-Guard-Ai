package com.zinminthet.plantguardai.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties("storage")
@AllArgsConstructor
@NoArgsConstructor
public class StorageProperties {
    private String location = "uploads";
}
