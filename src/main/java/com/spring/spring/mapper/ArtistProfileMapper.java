package com.spring.spring.mapper;

import com.spring.spring.dto.ArtistDTO;
import com.spring.spring.dto.ArtistProfileDTO;
import com.spring.spring.entity.Artist;
import com.spring.spring.entity.ArtistProfile;
import org.springframework.stereotype.Component;

@Component
public class ArtistProfileMapper {

    public ArtistProfileDTO toDTO(ArtistProfile artistProfile) {
        if (artistProfile == null) return null;
        return new ArtistProfileDTO(
                artistProfile.getId(),
                artistProfile.getWebsite()
        );
    }

    public ArtistProfile toEntity(ArtistProfileDTO artistProfileDTO) {
        if (artistProfileDTO == null) return null;
        ArtistProfile artistProfile = new ArtistProfile();
        artistProfile.setId(artistProfileDTO.getId());
        artistProfile.setWebsite(artistProfileDTO.getWebsite());
        return artistProfile;
    }
}
