package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistProfileDTO;
import com.spring.spring.entity.Artist;
import com.spring.spring.entity.ArtistProfile;
import com.spring.spring.mapper.ArtistMapper;
import com.spring.spring.mapper.ArtistProfileMapper;
import com.spring.spring.repository.ArtistProfileRepository;
import com.spring.spring.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistProfileImpl implements ArtistProfileService {
    private final ArtistProfileRepository artistProfileRepository;
    private final ArtistProfileMapper artistProfileMapper;
    private final ArtistRepository artistRepository;

    public ArtistProfileImpl(ArtistProfileRepository artistProfileRepository, ArtistProfileMapper artistProfileMapper, ArtistRepository artistRepository) {
        this.artistProfileRepository = artistProfileRepository;
        this.artistProfileMapper = artistProfileMapper;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<ArtistProfileDTO> getAllArtistProfiles() {
        return artistProfileRepository.findAll().stream()
                .map(artistProfileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDTO getArtistProfileById(int id) {
        Optional<ArtistProfile> artistProfile = artistProfileRepository.findById(id);
        if (artistProfile.isPresent()) {
            Optional<Artist> artist = artistRepository.findByProfileId(id);
            return ArtistMapper.toDTO(artist.get());
        } else {
            return null;
        }
    }

    @Override
    public ArtistProfileDTO createArtistProfile(ArtistProfileDTO artistProfileDTO) {
        // Convert DTO to entity
        var artistProfile = artistProfileMapper.toEntity(artistProfileDTO);

        // Save the entity to the repository
        var savedProfile = artistProfileRepository.save(artistProfile);

        // Convert a saved entity back to DTO
        return artistProfileMapper.toDTO(savedProfile);
    }

    @Override
    public boolean deleteArtistProfile(int id) {
        if (artistProfileRepository.existsById(id)) {
            artistProfileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
