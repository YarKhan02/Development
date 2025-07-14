package com.spring.spring.mapper;

import com.spring.spring.dto.ArtPieceDTO;
import com.spring.spring.dto.ArtPieceWithArtistDTO;
import com.spring.spring.entity.ArtCollection;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.Artist;
import com.spring.spring.projections.ArtPieceProjection;
import com.spring.spring.projections.ArtPieceWithArtistProjection;
import com.spring.spring.projections.CollectionIdProjection;
import com.spring.spring.projections.FlatArtPieceProjection;
import org.springframework.data.domain.Page;

import java.util.*;
import java.util.stream.Collectors;

public class ArtPieceMapper {

    public static ArtPieceDTO toDTO(ArtPiece art) {
        return new ArtPieceDTO(
                art.getId(),
                art.getTitle(),
                art.getDescription(),
                art.getPrice(),
                art.getArtist().getId(),
                art.getArtCollections()
                        .stream()
                        .map(ArtCollection::getId)
                        .collect(Collectors.toSet()),
                art.getType()
        );
    }

    public static ArtPieceDTO fromProjection(ArtPieceProjection projection) {
        return new ArtPieceDTO(
                projection.getId(),
                projection.getTitle(),
                projection.getDescription(),
                projection.getPrice(),
                projection.getArtistName(),
                projection.getArtCollections()
                        .stream()
                        .map(CollectionIdProjection::getId)
                        .collect(Collectors.toSet()),
                projection.getType()
        );
    }

    public static List<ArtPieceDTO> deduplicate(List<FlatArtPieceProjection> projections) {
        Map<Integer, ArtPieceDTO> map = new LinkedHashMap<>();

        for (FlatArtPieceProjection p : projections) {
            ArtPieceDTO dto = map.computeIfAbsent(p.getId(), id -> new ArtPieceDTO(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getArtistName(),
                    new HashSet<>(),
                    p.getType()
            ));

            CollectionIdProjection c = p.getArtCollection();
            if (c != null) {
                dto.getCollectionIds().add(c.getId());
            }
        }

        return new ArrayList<>(map.values());
    }

    public static List<ArtPieceDTO> deduplicate(Page<FlatArtPieceProjection> projections) {
        Map<Integer, ArtPieceDTO> map = new LinkedHashMap<>();

        for (FlatArtPieceProjection p : projections) {
            ArtPieceDTO dto = map.computeIfAbsent(p.getId(), id -> new ArtPieceDTO(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getArtistName(),
                    new HashSet<>(),
                    p.getType()
            ));

            CollectionIdProjection c = p.getArtCollection();
            if (c != null) {
                dto.getCollectionIds().add(c.getId());
            }
        }

        return new ArrayList<>(map.values());
    }


    public static ArtPieceWithArtistDTO fromArtPieceWithArtistProjection(ArtPieceWithArtistProjection art) {
        return new ArtPieceWithArtistDTO(
                art.getId(),
                art.getTitle(),
                art.getDescription(),
                art.getType(),
                art.getArtistName(),
                art.getArtistBio()
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
        art.setArtist(new Artist(dto.getArtistId(), null, null, null, null, null));
        art.setArtCollections(new HashSet<>());
        art.setType(dto.getType());
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
