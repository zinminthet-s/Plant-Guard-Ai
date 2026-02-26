package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Merchant;
import com.zinminthet.plantguardai.entities.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

//    @Query(nativeQuery = true,
//        value = ""
//    )
//    List<Pesticide> findAllPesticidesByMerchantId(Long merchantId);

}
