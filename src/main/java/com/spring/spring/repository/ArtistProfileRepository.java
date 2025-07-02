package com.spring.spring.repository;

import com.spring.spring.entity.ArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Integer> {
}
