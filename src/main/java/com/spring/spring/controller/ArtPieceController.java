package com.spring.spring.controller;

import com.spring.spring.dto.ArtPieceDTO;
import com.spring.spring.service.ArtPieceService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artpiece")
public class ArtPieceController {

    private final ArtPieceService artPieceService;

    @Autowired
    public ArtPieceController(ArtPieceService artPieceService) {
        this.artPieceService = artPieceService;
    }

    // --- ArtPiece CRUD ---

    // --- GET all artPieces ---
    @GetMapping
    public ResponseEntity<List<ArtPieceDTO>> getAllArtPieces() {
        return ResponseEntity.ok(artPieceService.getAllArtPieces());
    }

    // --- GET artPiece by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ArtPieceDTO> getArtPieceById(@PathVariable int id) {
        return artPieceService.getArtPieceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- GET artPieces by artist ID ---
    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<ArtPieceDTO>> getArtPiecesByArtistId(@PathVariable int artistId) {
        List<ArtPieceDTO> artPieces = artPieceService.getArtPiecesByArtistId(artistId);
        if (artPieces.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artPieces);
    }

    // --- POST create artPiece ---
    @PostMapping
    public ResponseEntity<ArtPieceDTO> createArtPiece(@Valid @RequestBody ArtPieceDTO artPieceDTO) {
        ArtPieceDTO saved = artPieceService.createArtPiece(artPieceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // --- PUT update artPiece ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtPiece(@PathVariable int id, @Valid @RequestBody ArtPieceDTO artPieceDTO) {
        return artPieceService.updateArtPiece(id, artPieceDTO)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("ArtPiece with ID " + id + " not found."));
    }

    // --- DELETE artPiece ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtPiece(@PathVariable int id) {
        boolean deleted = artPieceService.deleteArtPiece(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ArtPiece with ID " + id + " not found.");
        }
    }
}
