package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getUserList();
    UserEntity getUserByEmail(String email);
}
