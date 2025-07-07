package com.spring.spring.mapper;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ProfileDTO;
import com.spring.spring.entity.ArtPiece;
import com.spring.spring.entity.Artist;
import com.spring.spring.entity.ArtistProfile;
import com.spring.spring.projections.ArtPieceIdProjection;
import com.spring.spring.projections.DetailArtistProjection;
import com.spring.spring.projections.ProfileProjection;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Component
public class ArtistMapper {

    public static ArtistDTO toDTO(Artist artist) {
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getBio(),
                toProfileDTO(artist.getProfile()),
                artist.getArtPieces() == null
                        ? emptyList()
                        : artist.getArtPieces()
                        .stream()
                        .map(ArtPiece::getId)
                        .collect(Collectors.toList()),
                artist.getStatus()
        );
    }

    private static ProfileDTO toProfileDTO(ArtistProfile profile) {
        if (profile == null) return null;
        return new ProfileDTO(profile.getWebsite());
    }

    public static ArtistDTO fromProjection(DetailArtistProjection artist) {
        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getBio(),
                toProfileDTO(artist.getProfile()),
                artist.getArtPieces() == null
                        ? emptyList()
                        : artist.getArtPieces()
                        .stream()
                        .map(ArtPieceIdProjection::getId)
                        .collect(Collectors.toList())
        );
    }

    private static ProfileDTO toProfileDTO(ProfileProjection profile) {
        if (profile == null) return null;
        return new ProfileDTO(profile.getWebsite());
    }

    public static Artist toEntity(ArtistDTO dto, ArtistProfile existingProfile) {
        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setName(dto.getName());
        artist.setBio(dto.getBio());
        if (dto.getProfileId() != null) {
            artist.setProfile(existingProfile);
        }
        artist.setStatus(dto.getStatus());
        return artist;
    }

    public static void updateEntity(Artist artist, ArtistDTO dto) {
        if (artist == null || dto == null) return;
        if (dto.getName() != null) artist.setName(dto.getName());
        if (dto.getBio() != null) artist.setBio(dto.getBio());
    }
}