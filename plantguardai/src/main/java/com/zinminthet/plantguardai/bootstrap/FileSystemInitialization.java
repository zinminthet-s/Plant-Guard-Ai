package com.zinminthet.plantguardai.bootstrap;

import com.zinminthet.plantguardai.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileSystemInitialization implements CommandLineRunner {

    private final FileStorageService fileStorageService;

    @Override
    public void run(String... args) throws Exception {
        fileStorageService.init();
    }
}
