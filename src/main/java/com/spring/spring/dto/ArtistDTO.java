package com.spring.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {
    private int id;
    private String name;
    private String bio;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer profileId;
    private ProfileDTO profile;
    private List<Integer> artPieceIds;

    public ArtistDTO(int id, String name, String bio, ProfileDTO profile, List<Integer> artPieceIds) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.profile = profile;
        this.artPieceIds = artPieceIds;
    }
}