package com.spring.spring.mapper;

import com.spring.spring.dto.ArtPieceDTO;
import com.spring.spring.entity.ArtCollection;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.Artist;

import java.util.HashSet;
import java.util.stream.Collectors;

public class ArtPieceMapper {

    public static ArtPieceDTO toDTO(ArtPiece art) {
        return new ArtPieceDTO(
                art.getId(),
                art.getTitle(),
                art.getDescription(),
                art.getPrice(),
                art.getUploadedAt(),
                art.getArtist().getId(),
                art.getArtCollections()
                        .stream()
                        .map(ArtCollection::getId)
                        .collect(Collectors.toSet())
        );
    }

    public static ArtPiece toEntity(ArtPieceDTO dto) {
        ArtPiece art = new ArtPiece();
        art.setId(dto.getId());
        art.setTitle(dto.getTitle());
        art.setDescription(dto.getDescription());
        art.setPrice(dto.getPrice());
        art.setUploadedAt(dto.getUploadedAt());

        // Placeholders for artist and collections
        art.setArtist(new Artist(dto.getArtistId(), null, null, null));
        art.setArtCollections(new HashSet<>()); // Actual objects should be fetched elsewhere
        return art;
    }

    public static void updateEntity(ArtPiece art, ArtPieceDTO dto) {
        if (art == null || dto == null) return;
        if (dto.getTitle() != null) art.setTitle(dto.getTitle());
        if (dto.getDescription() != null) art.setDescription(dto.getDescription());
        if (dto.getPrice() != null) art.setPrice(dto.getPrice());
        if (dto.getUploadedAt() != null) art.setUploadedAt(dto.getUploadedAt());

        art.setArtCollections(new HashSet<>()); // Actual objects should be fetched elsewhere
    }
}
