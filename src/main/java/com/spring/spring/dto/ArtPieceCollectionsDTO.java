package com.spring.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtPieceCollectionsDTO {
    private int id;
    private String title;
    private String description;
    private BigDecimal price;
    private Set<CollectionInfoDTO> collections;
}
