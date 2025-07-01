package com.spring.spring.repository;

import com.spring.spring.entity.ArtCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtCollectionRepository extends JpaRepository<ArtCollection, Integer> {
    @Query("SELECT DISTINCT ac FROM ArtCollection ac LEFT JOIN FETCH ac.artPieces")
    List<ArtCollection> findAllWithArtPieces();
}