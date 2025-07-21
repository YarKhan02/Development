package com.spring.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String otp;
    private int attempts = 0;
    private boolean isVerified = false;
    private boolean isSuspended = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt;

    public OTP(String email, String otp, LocalDateTime expiresAt) {
        this.email = email;
        this.otp = otp;
        this.expiresAt = expiresAt;
    }

    public void incrementAttempts() {
        this.attempts++;
    }
}
