package com.spring.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.spring.enums.ArtPieceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtPieceDTO {
    private int id;
    private String title;
    private String description;
    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime uploadedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer artistId;                // Reference artist
    private Set<Integer> collectionIds; // Reference collections
    private ArtPieceType type;                // Type of art piece (e.g., PAINTING, SCULPTURE)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String artistName;

    public ArtPieceDTO(int id, String title, String description, BigDecimal price, int artistId, Set<Integer> collectionIds, ArtPieceType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.artistId = artistId;
        this.collectionIds = collectionIds;
        this.type = type;
    }

    public ArtPieceDTO(int id, String title, String description, BigDecimal price, String artistName, Set<Integer> collectionIds, ArtPieceType type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.artistName = artistName;
        this.collectionIds = collectionIds;
        this.type = type;
    }
}