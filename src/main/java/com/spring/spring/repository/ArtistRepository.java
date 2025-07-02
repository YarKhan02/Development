package com.spring.spring.repository;

import java.util.Optional;
import com.spring.spring.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByProfileId(int profileId);
}