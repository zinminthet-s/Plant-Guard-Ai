package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
    Optional<Auth> findByEmailAndEmailVerified(String email, Boolean isVerified);
}
