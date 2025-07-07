package com.spring.spring.repository;

import com.spring.spring.entity.ArtPiece;
import com.spring.spring.projections.ArtPieceProjection;
import com.spring.spring.projections.ArtPieceWithArtistProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtPieceRepository extends JpaRepository<ArtPiece, Integer> {
//    @Query("SELECT ap FROM ArtPiece ap JOIN FETCH ap.artist WHERE ap.artist.id = :artistId")
//    List<ArtPiece> findByArtistId(@Param("artistId") int artistId);

    @Query(value = "SELECT * FROM art_piece WHERE price > :amount", nativeQuery = true)
    List<ArtPiece> findByPrice(@Param("amount") BigDecimal amount);

    @Query("""
    SELECT a.id AS id,
           a.title AS title,
           a.description AS description,
           a.price AS price,
           a.artist.name AS artistName,
           a.type AS type,
           a.artCollections AS artCollections
    FROM ArtPiece a
    """)
    List<ArtPieceProjection> findAllProjectedArtPieces();

    @Query("""
    SELECT a.id AS id,
           a.title AS title,
           a.description AS description,
           a.price AS price,
           a.artist.name AS artistName,
           a.type AS type,
           a.artCollections AS artCollections
    FROM ArtPiece a
    WHERE a.id = :id
    """)
    Optional<ArtPieceProjection> findProjectedArtPieceById(@Param("id") int id);


    @Query("""
    SELECT a.id AS id,
           a.title AS title,
           a.description AS description,
           a.type AS type,
           a.artist.name AS artistName,
           a.artist.bio AS artistBio
    FROM ArtPiece a
    WHERE a.artist.id = :artistId
    """)
    List<ArtPieceWithArtistProjection> findByArtistId(@Param("artistId") int artistId);

}