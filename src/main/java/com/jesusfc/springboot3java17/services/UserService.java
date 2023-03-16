package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserEntity> getUserList();
    UserEntity getUserByEmail(String email);
    UserEntity getUserById(Long id);
    UserEntity saveUser(UserEntity userEntity);
    void deleteUserById(long userId);
    Page<UserEntity> getUserPageList(int pageNumber, int pageSize);
}
