package com.spring.spring.repository;

import java.util.List;
import java.util.Optional;
import com.spring.spring.entity.Artist;
import com.spring.spring.enums.ArtistStatus;
import com.spring.spring.projections.ArtistProjection;
import com.spring.spring.projections.DetailArtistProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT a.id AS id, a.name AS name, a.bio AS bio FROM Artist a WHERE a.id = :id")
    Optional<ArtistProjection> findProjected(@Param("id") Integer id);

    @Query("SELECT a FROM Artist a")
    Page<ArtistProjection> findAllProjectedWithPagination(Pageable pageable);

    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.profile LEFT JOIN FETCH a.artPieces WHERE a.id = :id")
    DetailArtistProjection findProjectedById(@Param("id") int id);

    @Query("SELECT a.id AS id, a.name AS name, a.bio AS bio FROM Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<ArtistProjection> findNamesByNameStarting(@Param("pattern") String pattern);

    @Query("SELECT a FROM Artist a WHERE " +
            "(:id IS NULL OR a.id = :id) AND " +
            "(:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:bio IS NULL OR LOWER(a.bio) LIKE LOWER(CONCAT('%', :bio, '%'))) AND " +
            "(:status IS NULL OR a.status = :status)")
    List<ArtistProjection> searchArtists(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("bio") String bio,
            @Param("status") ArtistStatus status
    );
}