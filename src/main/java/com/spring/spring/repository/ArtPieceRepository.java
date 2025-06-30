package com.spring.spring.repository;

import com.spring.spring.entity.ArtPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtPieceRepository extends JpaRepository<ArtPiece, Integer> {
    List<ArtPiece> findByArtistId(int artistId);
}