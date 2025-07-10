package com.spring.spring.service;


import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.dto.ArtCollectionDetailDTO;
import com.spring.spring.dto.ArtPieceCollectionsDTO;
import com.spring.spring.dto.CollectionSearchDTO;

import java.util.List;

public interface ArtCollectionService {

    List<ArtCollectionDetailDTO> getAllCollections();

    ArtCollectionDetailDTO getCollectionById(int id);

    ArtPieceCollectionsDTO getCollectionsByArtPiece(int id);

    List<ArtCollectionDetailDTO> searchCollections(CollectionSearchDTO searchDTO);

    ArtCollectionDetailDTO createCollection(ArtCollectionDTO dto);

    ArtCollectionDTO updateCollection(int id, ArtCollectionDTO dto);

    boolean deleteCollection(int id);
}