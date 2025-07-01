package com.spring.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ArtistProfile")
public class ArtistProfile {

    @Id
    private int id;

    private String website;

    @OneToOne
    @MapsId
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
