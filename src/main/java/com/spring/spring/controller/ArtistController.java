package com.spring.spring.controller;

import com.spring.spring.dto.ArtistDTO;

import com.spring.spring.entity.Artist;
import com.spring.spring.service.ArtistService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // --- Artist CRUD ---

    // --- GET all artists ---
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    // --- GET artist by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable int id) {
        ArtistDTO artist = artistService.getArtistById(id);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- POST create artist ---
    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        ArtistDTO saved = artistService.createArtist(artistDTO);
        return ResponseEntity.status(201).body(saved);
    }

    // --- PUT update artist ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable int id, @Valid @RequestBody ArtistDTO artistDTO) {
        ArtistDTO updated = artistService.updateArtist(id, artistDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Artist with ID " + id + " not found.");
        }
    }

    // --- DELETE artist ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable int id) {
        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Artist with ID " + id + " not found.");
        }
    }
}
