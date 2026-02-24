package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Cure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CureRepository extends JpaRepository<Cure, Long> {


    @Query(nativeQuery = true, name = "select * from diseases join cures on diseases.disease_id = cures.disease_id join pesticide_cure on cures.cure_id = pesticide_cure.cure_id where diseases.disease_name = :diseaseName;")
    Cure findCureByDiseaseName(@Param(value = "diseaseName") String diseaseName);

}
