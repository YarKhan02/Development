package com.spring.spring.controller;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistProfileDTO;
import com.spring.spring.service.ArtistProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistprofile")
public class ArtistProfileController {
    private final ArtistProfileService artistProfileService;

    @Autowired
    public ArtistProfileController(ArtistProfileService artistProfileService) {
        this.artistProfileService = artistProfileService;
    }

    // --- GET artist profiles ---
    @GetMapping
    public ResponseEntity<List<ArtistProfileDTO>> getAllArtistProfile() {
        List<ArtistProfileDTO> artistProfiles = artistProfileService.getAllArtistProfiles();
        if (artistProfiles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistProfiles);
    }

    // --- GET artist profile by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistProfileById(@PathVariable int id) {
        ArtistDTO artistProfile = artistProfileService.getArtistProfileById(id);
        if (artistProfile != null) {
            return ResponseEntity.ok(artistProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- POST create artist profile ---
    @PostMapping
    public ResponseEntity<ArtistProfileDTO> createArtistProfile(@RequestBody ArtistProfileDTO artistProfileDTO) {
        ArtistProfileDTO createdProfile = artistProfileService.createArtistProfile(artistProfileDTO);
        return ResponseEntity.status(201).body(createdProfile);
    }

    // --- DELETE artist profile by ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtistProfile(@PathVariable int id){
        boolean isDeleted = artistProfileService.deleteArtistProfile(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(501).build();
    }

}
