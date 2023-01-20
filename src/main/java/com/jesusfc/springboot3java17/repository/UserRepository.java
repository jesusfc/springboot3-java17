package com.jesusfc.springboot3java17.repository;

import com.jesusfc.springboot3java17.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
