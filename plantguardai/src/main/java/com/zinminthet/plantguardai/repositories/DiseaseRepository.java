package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {


    Disease findDiseaseByName(String name);

}
