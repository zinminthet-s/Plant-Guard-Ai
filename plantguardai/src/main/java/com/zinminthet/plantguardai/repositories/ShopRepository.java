package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
