package com.spring.spring.mapper;

import com.spring.spring.dto.*;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.ArtCollection;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArtCollectionMapper {

    // Converts ArtCollection entity to DTO
    public ArtCollectionDTO toDTO(ArtCollection collection) {
        return new ArtCollectionDTO(
                collection.getId(),
                collection.getTitle(),
                collection.getCuratorName(),
                collection.getArtPieces()
                        .stream()
                        .map(ArtPiece::getId)
                        .collect(Collectors.toList()),
                collection.getCategory()
        );
    }

    // Converts ArtPiece entity to a simple DTO
    // This is used in ArtCollectionDetailDTO to avoid circular references
    private SimpleArtPieceDTO toSimpleArtPieceDTO(ArtPiece artPiece) {
        return new SimpleArtPieceDTO(
                artPiece.getId(),
                artPiece.getTitle(),
                artPiece.getDescription(),
                artPiece.getPrice().doubleValue(),
                artPiece.getUploadedAt(),
                new SimpleArtistDTO(
                        artPiece.getArtist().getId(),
                        artPiece.getArtist().getName(),
                        artPiece.getArtist().getBio()
                )
        );
    }

    // Converts ArtCollection entity to detailed DTO
    // This includes the full list of ArtPieces
    public ArtCollectionDetailDTO toDetailDTO(ArtCollection collection) {
        return new ArtCollectionDetailDTO(
                collection.getId(),
                collection.getTitle(),
                collection.getCuratorName(),
                collection.getArtPieces().stream()
                        .map(this::toSimpleArtPieceDTO)
                        .collect(Collectors.toList()),
                collection.getCategory()
        );
    }

    public ArtPieceCollectionsDTO toArtPieceCollectionsDTO(ArtPiece artPiece) {
        return new ArtPieceCollectionsDTO(
                artPiece.getId(),
                artPiece.getTitle(),
                artPiece.getDescription(),
                artPiece.getPrice(),
                artPiece.getArtCollections()
                        .stream()
                        .map(collection -> new CollectionInfoDTO(
                                collection.getId(),
                                collection.getTitle(),
                                collection.getCuratorName(),
                                collection.getCategory()))
                        .collect(Collectors.toSet())
        );
    }

    // Converts DTO to ArtCollection entity
    public ArtCollection toEntity(ArtCollectionDTO dto) {
        ArtCollection col = new ArtCollection();
        col.setId(dto.getId());
        col.setTitle(dto.getTitle());
        col.setCuratorName(dto.getCuratorName());
        col.setArtPieces(null); // Should be set externally after fetching
        col.setCategory(dto.getCategory());
        return col;
    }

    // Updates an existing ArtCollection entity from DTO
    public void updateEntity(ArtCollection col, ArtCollectionDTO dto) {
        if (col == null || dto == null) return;
        if (dto.getTitle() != null) col.setTitle(dto.getTitle());
        if (dto.getCuratorName() != null) col.setCuratorName(dto.getCuratorName());
    }
}