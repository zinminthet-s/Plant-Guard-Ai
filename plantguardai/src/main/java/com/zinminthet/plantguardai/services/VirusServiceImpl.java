package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.entities.Virus;
import com.zinminthet.plantguardai.exceptions.VirusNotFound;
import com.zinminthet.plantguardai.repositories.VirusRepository;
import org.springframework.stereotype.Service;

@Service
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;

    public VirusServiceImpl(VirusRepository virusRepository) {
        this.virusRepository = virusRepository;
    }

    @Override
    public Virus addVirus(String name) {
        Virus virus = new Virus();
        virus.setName(name);
        var saved = virusRepository.save(virus);
        return saved;
    }

    @Override
    public void removeVirus(Long id) {
        var found = virusRepository.findById(id).orElseThrow(()->{
            throw new VirusNotFound("Virus is not found");
        });
        virusRepository.delete(found);
    }
}
