package com.jesusfc.springboot3java17.database.repository;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}