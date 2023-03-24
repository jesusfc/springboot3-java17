package com.jesusfc.springboot3java17.database.repository;


import com.jesusfc.springboot3java17.database.entity.VideoClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoClubRepository extends JpaRepository<VideoClubEntity, Long> {
    @Query(value = "SELECT * FROM video_clubs WHERE code = :naturalId", nativeQuery = true)
    Optional<VideoClubEntity> findByCode(@Param("naturalId") String naturalId);

}
