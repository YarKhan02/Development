package com.spring.spring.dto;

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
    private List<Integer> artPieceIds; // only reference ArtPiece IDs
}