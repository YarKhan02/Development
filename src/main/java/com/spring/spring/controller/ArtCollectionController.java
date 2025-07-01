package com.spring.spring.controller;

import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.dto.ArtCollectionDetailDTO;
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
    public ResponseEntity<ArtCollectionDTO> getCollectionById(@PathVariable int id) {
        ArtCollectionDTO dto = artCollectionService.getCollectionById(id);
        return ResponseEntity.notFound().build();
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