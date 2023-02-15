package com.jesusfc.springboot3java17.model.httpRQRS;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Data
@AllArgsConstructor
public class UserEntityListRS {
    List<UserEntity> userEntityList;
    public static UserEntityListRS userEntityListRS(List<UserEntity> userList){
        if (CollectionUtils.isEmpty(userList)) return new UserEntityListRS(List.of());
        return new UserEntityListRS(userList);
    }
}
