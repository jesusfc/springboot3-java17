package com.jesusfc.springboot3java17.database.repository;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Query(value = "SELECT usr.*, GROUP_CONCAT(urr.role) as roles " +
            "FROM users usr LEFT JOIN user_roles urr ON usr.id = urr.user_id, " +
            "users_video_clubs uvc, video_clubs vic " +
            "WHERE usr.email = :email " +
            "and usr.id = uvc.user_id and uvc.club_id = vic.id " +
            "and vic.code = :clubCode", nativeQuery = true)
    Optional<UserEntity> findUserForLogin(@Param("email") String email, @Param("clubCode") String clubCode);

}
