package com.spring.spring.repository;

import com.spring.spring.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OTPRepository extends JpaRepository<OTP, UUID> {
    Optional<OTP> findTopByEmailOrderByCreatedAtDesc(String email);
}
