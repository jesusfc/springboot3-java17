package com.jesusfc.springboot3java17.database.repository;

import com.jesusfc.springboot3java17.database.entity.UserFilmRentedEntity;
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
public interface UserFilmRentedRepository extends JpaRepository<UserFilmRentedEntity, Long> {

    @Query(value = "SELECT * FROM users_films_rented WHERE user_id = :user_id", nativeQuery = true)
    List<UserFilmRentedEntity> findByUserId(@Param("user_id") long userId);
}
