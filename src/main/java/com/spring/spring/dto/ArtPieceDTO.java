package com.spring.spring.dto;

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
    private LocalDateTime uploadedAt;

    private int artistId;                // Reference artist
    private Set<Integer> collectionIds; // Reference collections
}