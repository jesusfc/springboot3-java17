package com.jesusfc.springboot3java17.database.repository;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    UserEntity findByEmail(@Param("email") String email);
}
