package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistSearchDTO;
import com.spring.spring.entity.Artist;
import com.spring.spring.entity.ArtistProfile;
import com.spring.spring.exception.ResourceNotFoundException;
import com.spring.spring.mapper.ArtistMapper;
import com.spring.spring.projections.ArtistProjection;
import com.spring.spring.repository.ArtistProfileRepository;
import com.spring.spring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistProfileRepository artistProfileRepository;

    @Autowired
    public ArtistImpl(ArtistRepository artistRepository, ArtistProfileRepository artistProfileRepository) {
        this.artistRepository = artistRepository;
        this.artistProfileRepository = artistProfileRepository;
    }

    // GET all artists as DTOs
    @Override
    public List<ArtistProjection> getAllArtists() {
        return artistRepository.findAllProjected().stream().toList();
    }

    // GET artist by ID as DTO
    @Override
    public ArtistDTO getArtistById(int id) {
        return Optional.ofNullable(artistRepository.findProjectedById(id))
                .map(ArtistMapper::fromProjection)
                .orElse(null);
    }

    // GET artist-by-name search
    @Override
    public List<ArtistProjection> searchArtist(ArtistSearchDTO searchDTO) {
        return artistRepository.searchArtists(
                searchDTO.getId(),
                searchDTO.getName(),
                searchDTO.getBio(),
                searchDTO.getStatus()
                )
                .stream()
                .toList();
    }

    // POST create artist
    @Override
    public ArtistDTO createArtist(ArtistDTO dto) {
        if (dto.getProfileId() != null) {
            ArtistProfile profile = artistProfileRepository.findById(dto.getProfileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Artist Profile not found with ID: " + dto.getProfileId()));
            Artist artist = ArtistMapper.toEntity(dto, profile);
            Artist saved = artistRepository.save(artist);
            return ArtistMapper.toDTO(saved);
        }
        Artist artist = ArtistMapper.toEntity(dto, null);
        Artist saved = artistRepository.save(artist);
        return ArtistMapper.toDTO(saved);
    }

    // GET artist by status
    @Override
    public List<ArtistDTO> getArtistByStatus(ArtistDTO dto) {
        return artistRepository.findByStatus(dto.getStatus()).stream()
                .map(ArtistMapper::toDTO)
                .collect(Collectors.toList());
    }

    // PUT update artist
    @Override
    public ArtistDTO updateArtist(int id, ArtistDTO dto) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            Optional<ArtistProfile> profile = artistProfileRepository.findById(dto.getProfileId());
            if (profile.isPresent()) {
                artist.setProfile(profile.get());
            } else {
                artist.setProfile(null);
            }
            ArtistMapper.updateEntity(artist, dto);
            Artist updated = artistRepository.save(artist);
            return ArtistMapper.toDTO(updated);
        }
        return null;
    }

    // DELETE artist by ID
    @Override
    public boolean deleteArtist(int id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}