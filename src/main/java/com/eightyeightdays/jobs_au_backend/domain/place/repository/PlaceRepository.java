package com.eightyeightdays.jobs_au_backend.domain.place.repository;

import com.eightyeightdays.jobs_au_backend.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {

    // 단건 조회 — contacts JOIN FETCH (N+1 방지)
    @Query("SELECT p FROM Place p LEFT JOIN FETCH p.contacts WHERE p.id = :id")
    Optional<Place> findByIdWithContacts(@Param("id") Long id);

    // bounds 조회 — contacts JOIN FETCH (N+1 방지)
    @Query("SELECT DISTINCT p FROM Place p LEFT JOIN FETCH p.contacts " +
           "WHERE p.latitude BETWEEN :latMin AND :latMax " +
           "AND p.longitude BETWEEN :lngMin AND :lngMax " +
           "AND p.status = 'ACTIVE'")
    List<Place> findWithinBoundsWithContacts(
            @Param("latMin") Double latMin,
            @Param("latMax") Double latMax,
            @Param("lngMin") Double lngMin,
            @Param("lngMax") Double lngMax
    );

    // 페이지 조회 — contacts 없이 경량 조회 (목록용)
    @Query("SELECT p FROM Place p WHERE p.status = 'ACTIVE'")
    Page<Place> findAllActive(Pageable pageable);
}
