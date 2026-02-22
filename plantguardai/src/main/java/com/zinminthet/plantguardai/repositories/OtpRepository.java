package com.zinminthet.plantguardai.repositories;

import com.zinminthet.plantguardai.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmailAndCode(String email, int otpCode);
    void deleteAllByEmail(String email);
}