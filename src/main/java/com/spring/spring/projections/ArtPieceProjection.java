package com.spring.spring.projections;

import com.spring.spring.entity.ArtCollection;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.enums.ArtPieceType;

import java.math.BigDecimal;
import java.util.Set;

public interface ArtPieceProjection {
    int getId();
    String getTitle();
    String getDescription();
    BigDecimal getPrice();
    String getArtistName();
    Set<CollectionIdProjection> getArtCollections();
    ArtPieceType getType();
}
