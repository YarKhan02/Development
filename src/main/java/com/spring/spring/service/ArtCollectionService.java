package com.spring.spring.service;


import com.spring.spring.dto.ArtCollectionDTO;
import com.spring.spring.dto.ArtCollectionDetailDTO;

import java.util.List;

public interface ArtCollectionService {

    List<ArtCollectionDetailDTO> getAllCollections();

    ArtCollectionDTO getCollectionById(int id);

    ArtCollectionDetailDTO createCollection(ArtCollectionDTO dto);

    ArtCollectionDTO updateCollection(int id, ArtCollectionDTO dto);

    boolean deleteCollection(int id);
}