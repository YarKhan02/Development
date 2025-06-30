package com.spring.spring.repository;

import com.spring.spring.entity.ArtCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtCollectionRepository extends JpaRepository<ArtCollection, Integer> {
}