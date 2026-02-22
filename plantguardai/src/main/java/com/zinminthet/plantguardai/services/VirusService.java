package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.entities.Virus;

public interface VirusService {
    Virus addVirus(String name);
    void removeVirus(Long id);
}
