package com.jesusfc.springboot3java17.security;

import com.jesusfc.springboot3java17.database.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jesusfc
 * Created on mar 2023
 */
public class RoleAuthority implements GrantedAuthority {
    Set<RoleEntity> roles;

    public RoleAuthority(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String getAuthority() {
        return roles.stream().map(role -> role.getRoles().toString()).collect(Collectors.joining(","));

        /*
        StringBuilder stringBuilder = new StringBuilder();
        roles.forEach(roleEntity -> {
            stringBuilder.append(roleEntity.getRoles());
        });
        return stringBuilder.toString();

         */
    }
}
