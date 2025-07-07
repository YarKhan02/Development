package com.spring.spring.repository;

import java.util.List;
import java.util.Optional;
import com.spring.spring.entity.Artist;
import com.spring.spring.enums.ArtistStatus;
import com.spring.spring.projections.ArtistProjection;
import com.spring.spring.projections.DetailArtistProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByProfileId(int profileId);

    @Query("SELECT a FROM Artist a WHERE a.status = :status")
    List<Artist> findByStatus(@Param("status") ArtistStatus status);

    @Query("SELECT a.id AS id, a.name AS name, a.bio AS bio FROM Artist a")
    List<ArtistProjection> findAllProjected();

    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.profile LEFT JOIN FETCH a.artPieces WHERE a.id = :id")
    DetailArtistProjection findProjectedById(@Param("id") int id);
}