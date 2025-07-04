package com.spring.spring.repository;

import java.util.List;
import java.util.Optional;
import com.spring.spring.entity.Artist;
import com.spring.spring.enums.ArtistStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByProfileId(int profileId);

    @Query("SELECT a FROM Artist a WHERE a.status = :status")
    List<Artist> findByStatus(@Param("status") ArtistStatus status);
}