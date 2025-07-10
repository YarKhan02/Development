package com.spring.spring.repository;

import com.spring.spring.entity.ArtCollection;
import com.spring.spring.enums.CollectionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtCollectionRepository extends JpaRepository<ArtCollection, Integer> {
    @Query("SELECT DISTINCT ac FROM ArtCollection ac LEFT JOIN FETCH ac.artPieces")
    List<ArtCollection> findAllWithArtPieces();

    @Query("SELECT DISTINCT ac FROM ArtCollection ac " +
            "LEFT JOIN FETCH ac.artPieces ap " +
            "LEFT JOIN FETCH ap.artist a " +
            "WHERE (:title IS NULL OR LOWER(ac.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:curatorName IS NULL OR LOWER(ac.curatorName) LIKE LOWER(CONCAT('%', :curatorName, '%'))) " +
            "AND (:category IS NULL OR ac.category = :category) " +
            "AND (:artPieceTitle IS NULL OR LOWER(ap.title) LIKE LOWER(CONCAT('%', :artPieceTitle, '%'))) " +
            "AND (:artistName IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :artistName, '%')))")
    List<ArtCollection> searchCollections(
            @Param("title") String title,
            @Param("curatorName") String curatorName,
            @Param("category") CollectionCategory category,
            @Param("artPieceTitle") String artPieceTitle,
            @Param("artistName") String artistName
    );
}