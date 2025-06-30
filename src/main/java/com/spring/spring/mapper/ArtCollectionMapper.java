package com.spring.spring.mapper;

import com.spring.spring.dto.ArtCollectionDTO;
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
                        .collect(Collectors.toList())
        );
    }

    // Converts DTO to ArtCollection entity
    public ArtCollection toEntity(ArtCollectionDTO dto) {
        ArtCollection col = new ArtCollection();
        col.setId(dto.getId());
        col.setTitle(dto.getTitle());
        col.setCuratorName(dto.getCuratorName());
        col.setArtPieces(null); // Should be set externally after fetching
        return col;
    }

    // Updates an existing ArtCollection entity from DTO
    public void updateEntity(ArtCollection col, ArtCollectionDTO dto) {
        if (col == null || dto == null) return;
        if (dto.getTitle() != null) col.setTitle(dto.getTitle());
        if (dto.getCuratorName() != null) col.setCuratorName(dto.getCuratorName());
        col.setArtPieces(null); // Should be set externally after fetching
    }
}