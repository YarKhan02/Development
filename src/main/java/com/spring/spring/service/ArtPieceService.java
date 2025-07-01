package com.spring.spring.service;

import com.spring.spring.dto.ArtPieceDTO;

import java.util.List;
import java.util.Optional;

public interface ArtPieceService {

    List<ArtPieceDTO> getAllArtPieces();

    Optional<ArtPieceDTO> getArtPieceById(int id);

    List<ArtPieceDTO> getArtPiecesByArtistId(int artistId);

    ArtPieceDTO createArtPiece(ArtPieceDTO artPieceDTO);

    Optional<ArtPieceDTO> updateArtPiece(int id, ArtPieceDTO artPieceDTO);

    boolean deleteArtPiece(int id);
}
