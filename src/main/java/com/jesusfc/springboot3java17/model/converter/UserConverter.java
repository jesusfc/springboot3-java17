package com.jesusfc.springboot3java17.model.converter;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.openApi.v1.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserConverter implements ConverterAll<UserEntity, User> {

    @Override
    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setFamilyName(userEntity.getFamilyName());
        user.setEmail(userEntity.getEmail());
        user.setEnable(userEntity.isEnabled());
        user.setCreateAt(userEntity.getCreateAt().toLocalDate());
        user.setVideoClub(new VideoClubConverter().convertList(userEntity.getVideoClubs()));
        return user;
    }

    @Override
    public List<User> convertList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::convert).toList();
    }

    @Override
    public UserEntity convertSource(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .familyName(user.getFamilyName())
                .email(user.getEmail())
                .enabled(user.isEnable())
                .createAt(LocalDateTime.now())
                .build();
    }
    @Override
    public List<UserEntity> convertSourceList(List<User> userList) {
        return userList.stream().map(this::convertSource).toList();
    }

}
