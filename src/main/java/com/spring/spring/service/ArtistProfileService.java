package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistProfileDTO;

import java.util.List;

public interface ArtistProfileService {
    // Method to get all artist profiles
    List<ArtistProfileDTO> getAllArtistProfiles();

    // Method to get an artist profile by ID
    ArtistDTO getArtistProfileById(int id);

    // Method to create a new artist profile
    ArtistProfileDTO createArtistProfile(ArtistProfileDTO artistProfileDTO);

    // Method to update an existing artist profile
//    Optional<ArtistProfileDTO> updateArtistProfile(int id, ArtistProfileDTO artistProfileDTO);

    // Method to delete an artist profile
    boolean deleteArtistProfile(int id);
}
