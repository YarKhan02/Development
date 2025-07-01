package com.spring.spring.dto;

import com.spring.spring.entity.ArtPiece;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

// DTO class for full details
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtCollectionDetailDTO {
    private int id;
    private String title;
    private String curatorName;
    private List<SimpleArtPieceDTO> artPieces;
}