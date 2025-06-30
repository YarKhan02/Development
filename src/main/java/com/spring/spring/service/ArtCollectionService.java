package com.spring.spring.service;


import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.entity.ArtCollection;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.mapper.ArtCollectionMapper;
import com.spring.spring.repository.ArtCollectionRepository;
import com.spring.spring.repository.ArtPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtCollectionService {

    private final ArtCollectionRepository artCollectionRepository;
    private final ArtPieceRepository artPieceRepository;
    private final ArtCollectionMapper artCollectionMapper;

    @Autowired
    public ArtCollectionService(ArtCollectionRepository artCollectionRepository, ArtPieceRepository artPieceRepository, ArtCollectionMapper artCollectionMapper) {
        this.artCollectionRepository = artCollectionRepository;
        this.artPieceRepository = artPieceRepository;
        this.artCollectionMapper = artCollectionMapper;
    }

    // Get all art collections as DTOs
    public List<ArtCollectionDTO> getAllCollections() {
        return artCollectionRepository.findAll().stream()
                .map(artCollectionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get a specific art collection by ID as DTO
    public ArtCollectionDTO getCollectionById(int id) {
        return artCollectionRepository.findById(id)
                .map(artCollectionMapper::toDTO)
                .orElse(null);
    }

    // Create a new art collection and return as DTO
    public ArtCollectionDTO createCollection(ArtCollectionDTO dto) {
        Set<Integer> artPieceIds = dto.getArtPieceIds() != null
                ? new HashSet<>(dto.getArtPieceIds())
                : Set.of();
        Set<ArtPiece> artPieces = artPieceIds.stream()
                .map(artPieceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        artPieces.forEach(artPiece -> System.out.println("ArtPiece ID: " + artPiece.getId()));
        ArtCollection entity = artCollectionMapper.toEntity(dto);
        entity.setArtPieces(artPieces);
        ArtCollection saved = artCollectionRepository.save(entity);
        return artCollectionMapper.toDTO(saved);
    }

    // Update an existing art collection and return as DTO if found
    public ArtCollectionDTO updateCollection(int id, ArtCollectionDTO dto) {
        return artCollectionRepository.findById(id).map(existing -> {
            existing.setTitle(dto.getTitle());
            existing.setCuratorName(dto.getCuratorName());
            Set<ArtPiece> artPieces = dto.getArtPieceIds().stream()
                    .map(artPieceRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            existing.setArtPieces(artPieces);
            ArtCollection updated = artCollectionRepository.save(existing);
            return artCollectionMapper.toDTO(updated);
        }).orElse(null);
    }

    // Delete an art collection by ID, return true if deleted
    public boolean deleteCollection(int id) {
        if (artCollectionRepository.existsById(id)) {
            artCollectionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}