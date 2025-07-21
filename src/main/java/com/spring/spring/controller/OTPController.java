package com.spring.spring.controller;

import com.spring.spring.dto.OTPRequestDTO;
import com.spring.spring.dto.OTPVerifyRequestDTO;
import com.spring.spring.dto.OTPVerifyResponseDTO;
import com.spring.spring.service.OTPService;
import com.spring.spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OTPController {

    private final OTPService otpService;

    public OTPController(OTPService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/request-otp")
    public ResponseEntity<String> requestOTP(@RequestBody OTPRequestDTO req) {
        otpService.generateAndSendOTP(req.getEmail());
        return ResponseEntity.ok("OTP sent successfully to " + req.getEmail());
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<OTPVerifyResponseDTO> verifyOTP(@RequestBody OTPVerifyRequestDTO req, HttpSession session) {
        return otpService.verifyOTPAndCreateUser(req.getEmail(), req.getOtp(), session);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

        return ResponseEntity.ok("Welcome to dashboard: " + email);
    }
}
