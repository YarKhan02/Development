package com.spring.spring.controller;

import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.dto.ArtCollectionDetailDTO;
import com.spring.spring.dto.ArtPieceCollectionsDTO;
import com.spring.spring.dto.CollectionSearchDTO;
import com.spring.spring.service.ArtCollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/art-collection")
public class ArtCollectionController {

    private final ArtCollectionService artCollectionService;

    @Autowired
    public ArtCollectionController(ArtCollectionService artCollectionService) {
        this.artCollectionService = artCollectionService;
    }

    // --- ArtCollection CRUD ---

    // --- GET all art collections ---
    @GetMapping
    public List<ArtCollectionDetailDTO> getAllCollections() {
        return artCollectionService.getAllCollections();
    }

    // --- GET art collection by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ArtCollectionDetailDTO> getCollectionById(@PathVariable int id) {
        ArtCollectionDetailDTO dto = artCollectionService.getCollectionById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // --- GET all art collections where an art piece belongs to ---
    @GetMapping("/art/{id}")
    public ResponseEntity<ArtPieceCollectionsDTO> getCollectionsByArtPiece(@PathVariable int id) {
        ArtPieceCollectionsDTO dto = artCollectionService.getCollectionsByArtPiece(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<ArtCollectionDetailDTO>> searchCollections(@RequestBody CollectionSearchDTO searchDTO) {
        List<ArtCollectionDetailDTO> results = artCollectionService.searchCollections(searchDTO);
        return ResponseEntity.ok(results);
    }

    // --- POST create art collection ---
    @PostMapping
    public ResponseEntity<ArtCollectionDetailDTO> createCollection(@RequestBody ArtCollectionDTO dto) {
        ArtCollectionDetailDTO created = artCollectionService.createCollection(dto);
        return ResponseEntity.ok(created);
    }

    // --- PUT update art collection ---
    @PutMapping("/{id}")
    public ResponseEntity<ArtCollectionDTO> updateCollection(@PathVariable int id, @RequestBody ArtCollectionDTO dto) {
        ArtCollectionDTO updated = artCollectionService.updateCollection(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // --- DELETE art collection by ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable int id) {
        boolean deleted = artCollectionService.deleteCollection(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}