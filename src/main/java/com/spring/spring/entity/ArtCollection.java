package com.spring.spring.entity;

import com.spring.spring.enums.CollectionCategory;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "art_collection")
public class ArtCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String curatorName;

    @ManyToMany(mappedBy = "artCollections")
    private Set<ArtPiece> artPieces;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CollectionCategory category;
}