package com.spring.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleArtPieceDTO {
    private int id;
    private String title;
    private String description;
    private double price;
    private LocalDateTime uploadedAt;
    private SimpleArtistDTO artist;
}