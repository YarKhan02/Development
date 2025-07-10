package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistSearchDTO;
import com.spring.spring.projections.ArtistProjection;

import java.util.List;

public interface ArtistService {

    List<ArtistProjection> getAllArtists();

    ArtistDTO getArtistById(int id);

    List<ArtistProjection> searchArtist(ArtistSearchDTO searchDTO);

    ArtistDTO createArtist(ArtistDTO dto);

    List<ArtistDTO> getArtistByStatus(ArtistDTO dto);

    ArtistDTO updateArtist(int id, ArtistDTO dto);

    boolean deleteArtist(int id);
}