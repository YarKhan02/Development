package com.spring.spring.projections;

import com.spring.spring.enums.ArtPieceType;

public interface ArtPieceWithArtistProjection {
    int getId();
    String getTitle();
    String getDescription();
    ArtPieceType getType();
    String getArtistName();
    String getArtistBio();
}

