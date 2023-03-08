package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getUserList();
    UserEntity getUserByEmail(String email);
    Optional<UserEntity> getUserById(Long id);
    UserEntity saveUser(UserEntity userEntity);
    void deleteUserById(long userId);
    List<UserEntity> getUserPageList(int pageNumber, int pageSize);
}
