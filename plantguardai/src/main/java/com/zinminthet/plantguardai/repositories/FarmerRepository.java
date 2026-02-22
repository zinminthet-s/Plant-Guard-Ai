package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
