package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.entity.Artist;
import com.spring.spring.mapper.ArtistMapper;
import com.spring.spring.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Get all artists as DTOs
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(ArtistMapper::toDTO)
                .toList();
    }

    // Get an artist by ID as DTO
    public Optional<ArtistDTO> getArtistById(int id) {
        return artistRepository.findById(id)
                .map(ArtistMapper::toDTO);
    }

    // Create a new artist and return as DTO
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        Artist artist = ArtistMapper.toEntity(artistDTO);
        Artist saved = artistRepository.save(artist);
        return ArtistMapper.toDTO(saved);
    }

    // Update an existing artist and return as DTO if found
    public Optional<ArtistDTO> updateArtist(int id, ArtistDTO artistDTO) {
        return artistRepository.findById(id)
                .map(existingArtist -> {
                    ArtistMapper.updateEntity(existingArtist, artistDTO);
                    Artist saved = artistRepository.save(existingArtist);
                    return ArtistMapper.toDTO(saved);
                });
    }

    // Delete an artist by ID, return true if deleted
    public boolean deleteArtist(int id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
