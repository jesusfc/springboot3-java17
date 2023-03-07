package com.jesusfc.springboot3java17.model.converter;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.openApi.v1.model.User;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class UserConverter implements Converter<UserEntity, User> {

    @Override
    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setFamilyName(userEntity.getFamilyName());
        user.setEmail(userEntity.getEmail());
        user.setEnable(userEntity.isEnabled());
        user.setCreateAt(userEntity.getCreateAt().toLocalDate());
        return user;
    }
    public List<User> convertList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::convert).toList();
    }
}
