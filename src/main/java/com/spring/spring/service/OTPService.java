package com.spring.spring.service;

import com.spring.spring.dto.OTPVerifyResponseDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface OTPService {

    void generateAndSendOTP(String email);

    ResponseEntity<OTPVerifyResponseDTO> verifyOTPAndCreateUser(String email, String otp, HttpSession session);
}
