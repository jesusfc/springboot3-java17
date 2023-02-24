package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getUserList();
    Optional<UserEntity> getUserByEmail(String email);
    UserEntity saveUser(UserEntity userEntity);
}
