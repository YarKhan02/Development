package com.spring.spring.projections;

import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.ArtistProfile;

import java.util.List;

public interface DetailArtistProjection {
    int getId();
    String getName();
    String getBio();
    ProfileProjection getProfile();
    List<ArtPieceIdProjection> getArtPieces();
}
