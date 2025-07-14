package com.spring.spring.projections;

import com.spring.spring.enums.ArtPieceType;

import java.math.BigDecimal;

public interface FlatArtPieceProjection {
    int getId();
    String getTitle();
    String getDescription();
    BigDecimal getPrice();
    String getArtistName();
    ArtPieceType getType();
    CollectionIdProjection getArtCollection(); // nullable
}