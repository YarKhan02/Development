package com.spring.spring.service;

import com.spring.spring.dto.ArtPieceDTO;
import com.spring.spring.dto.ArtPieceWithArtistDTO;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.Artist;
import com.spring.spring.mapper.ArtPieceMapper;
import com.spring.spring.projections.FlatArtPieceProjection;
import com.spring.spring.repository.ArtPieceRepository;
import com.spring.spring.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtPieceImpl implements ArtPieceService {

    private final ArtPieceRepository artPieceRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtPieceImpl(ArtPieceRepository artPieceRepository, ArtistRepository artistRepository) {
        this.artPieceRepository = artPieceRepository;
        this.artistRepository = artistRepository;
    }

    // Get all art pieces as DTOs
    public List<ArtPieceDTO> getAllArtPieces() {
        List<FlatArtPieceProjection> projections = artPieceRepository.findAllFlatProjectedArtPieces();
        return ArtPieceMapper.deduplicate(projections);
    }

    // Get an art piece by ID as DTO
    public Optional<ArtPieceDTO> getArtPieceById(int id) {
        return artPieceRepository.findProjectedArtPieceById(id)
                .map(ArtPieceMapper::fromProjection);
    }

    // Get all art pieces by artist ID as DTOs
    public List<ArtPieceWithArtistDTO> getArtPiecesByArtistId(int artistId) {
        return artPieceRepository.findByArtistId(artistId)
                .stream()
                .map(ArtPieceMapper::fromArtPieceWithArtistProjection)
                .toList();
    }

    // Get all art pieces with pagination
    public Page<ArtPieceDTO> getAllArtPiecesPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Integer> pageIds = artPieceRepository.findPaginatedArtPieceIds(pageable);
        if (pageIds.isEmpty()) { return Page.empty(); }

        List<FlatArtPieceProjection> projections = artPieceRepository.findFlatArtPiecesByIds(pageIds.getContent());

        List<ArtPieceDTO> deduped = ArtPieceMapper.deduplicate(projections);

        return new PageImpl<>(deduped, pageable, pageIds.getTotalElements());
    }

    // Get all art pieces by price as DTOs
    public List<ArtPieceDTO> getArtPiecesByPrice(ArtPieceDTO price) {
        return artPieceRepository.findByPrice(price.getPrice())
                .stream()
                .map(ArtPieceMapper::toDTO)
                .toList();
    }

    // Create a new artPiece and return as DTO
    public List<ArtPieceDTO> createArtPiece(List<ArtPieceDTO> artPieceDTO) {
        List<ArtPiece> artPiece = artPieceDTO.stream()
                .map(ArtPieceMapper::toEntity)
                .collect(Collectors.toList());
        List<ArtPiece> saved = artPieceRepository.saveAll(artPiece);

        return saved.stream()
                .map(ArtPieceMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Update an existing artPiece and return as DTO if found
    public Optional<ArtPieceDTO> updateArtPiece(int id, ArtPieceDTO artPieceDTO) {
        return artPieceRepository.findById(id)
                .map(existingArtPiece -> {
                    ArtPieceMapper.updateEntity(existingArtPiece, artPieceDTO);
                    Artist artist = artistRepository.findById(existingArtPiece.getArtist().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Artist not found for ID: " + existingArtPiece.getArtist().getId()));
                    ArtPiece saved = artPieceRepository.save(existingArtPiece);
                    return ArtPieceMapper.toDTO(saved);
                });
    }

    // Delete a course by ID, return true if deleted
    public boolean deleteArtPiece(int id) {
        if (artPieceRepository.existsById(id)) {
            artPieceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}