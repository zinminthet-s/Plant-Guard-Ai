package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesticideRepository extends JpaRepository<Pesticide, Long> {

    @Query(
            value = "SELECT * FROM pesticides WHERE MATCH(pesticide_name) AGAINST (:query IN BOOLEAN MODE)",
            nativeQuery = true
    )
    List<Pesticide> searchPesticideByQuery(@Param("query") String query);
}
