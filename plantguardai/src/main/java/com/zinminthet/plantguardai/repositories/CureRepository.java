package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Cure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CureRepository extends JpaRepository<Cure, Long> {
}
