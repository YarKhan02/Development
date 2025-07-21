package com.spring.spring.service;

import com.spring.spring.dto.OTPVerifyResponseDTO;
import com.spring.spring.emailservice.EmailService;
import com.spring.spring.entity.OTP;
import com.spring.spring.repository.OTPRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OTPImpl implements OTPService {

    private final OTPRepository otpRepository;
    private final EmailService emailService;
    private final UserService userService;

    public OTPImpl(OTPRepository otpRepository, EmailService emailService, UserService userService) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    public void generateAndSendOTP(String email) {
        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));
        OTP entity = new OTP(email, otp, LocalDateTime.now().plusMinutes(10));
        otpRepository.save(entity);
        emailService.sendEmail(email, "OTP", "Your OTP is: " + otp);
    }

    private boolean verifyOTP(String email, String otp) {
        Optional<OTP> record = otpRepository.findTopByEmailOrderByCreatedAtDesc(email);

        if (record.isEmpty()) {
            return false;
        }

        OTP entity = record.get();

        if (entity.getExpiresAt().isBefore(LocalDateTime.now()) || !entity.getOtp().equals(otp)) {
            entity.incrementAttempts();
            if (entity.getAttempts() >= 3) {
                entity.setSuspended(true);
            }
            otpRepository.save(entity);
            return false;
        }

        entity.setVerified(true);
        otpRepository.save(entity);

        return true;
    }

    @Override
    public ResponseEntity<OTPVerifyResponseDTO> verifyOTPAndCreateUser(String email, String otp, HttpSession session) {
        boolean verified = verifyOTP(email, otp);

        if (!verified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new OTPVerifyResponseDTO("Invalid OTP or expired OTP", null));
        }

        userService.createUser(email);
        session.setAttribute("email", email);

        return ResponseEntity.ok(new OTPVerifyResponseDTO("OTP verified successfully", session.getId()));
    }
}
