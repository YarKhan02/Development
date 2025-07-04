package com.spring.spring.service;

import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.dto.ArtCollectionDetailDTO;
import com.spring.spring.dto.ArtPieceCollectionsDTO;
import com.spring.spring.entity.ArtCollection;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.mapper.ArtCollectionMapper;
import com.spring.spring.mapper.ArtPieceMapper;
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
public class ArtCollectionImpl implements ArtCollectionService {

    private final ArtCollectionRepository artCollectionRepository;
    private final ArtPieceRepository artPieceRepository;
    private final ArtCollectionMapper artCollectionMapper;

    @Autowired
    public ArtCollectionImpl(ArtCollectionRepository artCollectionRepository, ArtPieceRepository artPieceRepository, ArtCollectionMapper artCollectionMapper) {
        this.artCollectionRepository = artCollectionRepository;
        this.artPieceRepository = artPieceRepository;
        this.artCollectionMapper = artCollectionMapper;
    }

    @Override
    public List<ArtCollectionDetailDTO> getAllCollections() {
        return artCollectionRepository.findAllWithArtPieces().stream()
                .map(artCollectionMapper::toDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtCollectionDetailDTO getCollectionById(int id) {
        return artCollectionRepository.findById(id)
                .map(artCollectionMapper::toDetailDTO)
                .orElse(null);
    }

    @Override
    public ArtPieceCollectionsDTO getCollectionsByArtPiece(int id) {
        ArtPiece artPiece = artPieceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ArtPiece not found with id: " + id));
        return artCollectionMapper.toArtPieceCollectionsDTO(artPiece);
    }

    @Override
    public ArtCollectionDetailDTO createCollection(ArtCollectionDTO dto) {
        Set<Integer> artPieceIds = dto.getArtPieceIds() != null
                ? new HashSet<>(dto.getArtPieceIds())
                : Set.of();
        Set<ArtPiece> artPieces = artPieceIds.stream()
                .map(artPieceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        ArtCollection entity = artCollectionMapper.toEntity(dto);
        entity.setArtPieces(artPieces);
        artPieces.forEach(ap -> ap.getArtCollections().add(entity));
        ArtCollection saved = artCollectionRepository.save(entity);
        return artCollectionMapper.toDetailDTO(saved);
    }

    @Override
    public ArtCollectionDTO updateCollection(int id, ArtCollectionDTO dto) {
        return artCollectionRepository.findById(id).map(existing -> {
            artCollectionMapper.updateEntity(existing, dto);
            ArtCollection updated = artCollectionRepository.save(existing);
            return artCollectionMapper.toDTO(updated);
        }).orElse(null);
    }

    @Override
    public boolean deleteCollection(int id) {
        if (artCollectionRepository.existsById(id)) {
            artCollectionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}