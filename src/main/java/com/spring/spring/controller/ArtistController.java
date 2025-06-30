package com.spring.spring.controller;

import com.spring.spring.dto.ArtistDTO;

import com.spring.spring.service.ArtistService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return artistService.getArtistById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        return artistService.updateArtist(id, artistDTO)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Artist with ID " + id + " not found."));
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
