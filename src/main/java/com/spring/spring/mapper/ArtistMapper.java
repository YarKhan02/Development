package com.spring.spring.mapper;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.Artist;

import java.util.stream.Collectors;

public class ArtistMapper {

    public static ArtistDTO toDTO(Artist artist) {
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getBio(),
                artist.getArtPieces()
                        .stream()
                        .map(ArtPiece::getId)
                        .collect(Collectors.toList())
        );
    }

    public static Artist toEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setName(dto.getName());
        artist.setBio(dto.getBio());
        return artist;
    }

    public static void updateEntity(Artist artist, ArtistDTO dto) {
        if (artist == null || dto == null) return;
        if (dto.getName() != null) artist.setName(dto.getName());
        if (dto.getBio() != null) artist.setBio(dto.getBio());
    }
}