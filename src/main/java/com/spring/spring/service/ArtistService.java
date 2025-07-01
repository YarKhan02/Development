package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;

import java.util.List;

public interface ArtistService {

    List<ArtistDTO> getAllArtists();

    ArtistDTO getArtistById(int id);

    ArtistDTO createArtist(ArtistDTO dto);

    ArtistDTO updateArtist(int id, ArtistDTO dto);

    boolean deleteArtist(int id);
}