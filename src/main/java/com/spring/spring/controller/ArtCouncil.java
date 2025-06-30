package com.spring.spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ArtCouncil {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Art Council!";
    }
}