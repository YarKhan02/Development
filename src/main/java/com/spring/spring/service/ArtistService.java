package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistSearchDTO;
import com.spring.spring.projections.ArtistProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArtistService {

    List<ArtistProjection> getAllArtists();

    ArtistDTO getArtistById(int id);

    List<ArtistProjection> searchArtist(ArtistSearchDTO searchDTO);

    Page<ArtistDTO> getArtistsWithPagination(int page, int size);

    ArtistDTO createArtist(ArtistDTO dto);

    List<ArtistDTO> getArtistByStatus(ArtistDTO dto);

    ArtistDTO updateArtist(int id, ArtistDTO dto);

    boolean deleteArtist(int id);
}