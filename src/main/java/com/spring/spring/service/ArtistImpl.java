package com.spring.spring.service;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.entity.Artist;
import com.spring.spring.mapper.ArtistMapper;
import com.spring.spring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDTO getArtistById(int id) {
        return artistRepository.findById(id)
                .map(ArtistMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ArtistDTO createArtist(ArtistDTO dto) {
        Artist artist = ArtistMapper.toEntity(dto);
        Artist saved = artistRepository.save(artist);
        return ArtistMapper.toDTO(saved);
    }

    @Override
    public ArtistDTO updateArtist(int id, ArtistDTO dto) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            ArtistMapper.updateEntity(artist, dto);
            Artist updated = artistRepository.save(artist);
            return ArtistMapper.toDTO(updated);
        }
        return null;
    }

    @Override
    public boolean deleteArtist(int id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}