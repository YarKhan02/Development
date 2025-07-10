package com.spring.spring.dto;

import com.spring.spring.enums.ArtistStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistSearchDTO {
    private Integer id;
    private String name;
    private String bio;
    private ArtistStatus status;
}
