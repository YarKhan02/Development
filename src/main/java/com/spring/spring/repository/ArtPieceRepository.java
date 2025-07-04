package com.spring.spring.repository;

import com.spring.spring.entity.ArtPiece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ArtPieceRepository extends JpaRepository<ArtPiece, Integer> {
    @Query("SELECT ap FROM ArtPiece ap JOIN FETCH ap.artist WHERE ap.artist.id = :artistId")
    List<ArtPiece> findByArtistId(@Param("artistId") int artistId);

    @Query("SELECT ap FROM ArtPiece ap WHERE ap.price > :amount")
    List<ArtPiece> findByPrice(@Param("amount") BigDecimal amount);
}