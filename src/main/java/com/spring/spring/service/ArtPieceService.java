package com.spring.spring.service;

import com.spring.spring.dto.ArtPieceDTO;
import com.spring.spring.dto.ArtPieceWithArtistDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ArtPieceService {

    List<ArtPieceDTO> getAllArtPieces();

    Optional<ArtPieceDTO> getArtPieceById(int id);

    List<ArtPieceWithArtistDTO> getArtPiecesByArtistId(int artistId);

    List<ArtPieceDTO> getArtPiecesByPrice(ArtPieceDTO price);

    Page<ArtPieceDTO> getAllArtPiecesPagination(int page, int size);

    List<ArtPieceDTO> createArtPiece(List<ArtPieceDTO> artPieceDTO);

    Optional<ArtPieceDTO> updateArtPiece(int id, ArtPieceDTO artPieceDTO);

    boolean deleteArtPiece(int id);
}
