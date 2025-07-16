package com.spring.spring.dto.external;

import com.spring.spring.dto.ArtistDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentArtistDTO {
    private StudentDTO student;
    private ArtistDTO artist;
}
