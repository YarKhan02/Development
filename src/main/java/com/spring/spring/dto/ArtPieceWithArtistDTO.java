package com.spring.spring.dto;

import com.spring.spring.enums.ArtPieceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtPieceWithArtistDTO {
    private int id; // ArtPiece ID
    private String title; // ArtPiece title
    private String description; // ArtPiece description
    private ArtPieceType type; // ArtPiece type (e.g., PAINTING, SCULPTURE)
    private String artistName; // Artist's name
    private String artistBio; // Artist's bio
}
