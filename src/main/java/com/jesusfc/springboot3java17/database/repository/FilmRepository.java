package com.jesusfc.springboot3java17.database.repository;

import com.jesusfc.springboot3java17.database.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    @Query(value = "SELECT * FROM films WHERE video_club_id = :video_club_id", nativeQuery = true)
    List<FilmEntity> findByVideoClubId(@Param("video_club_id") long videoClubId);
}
