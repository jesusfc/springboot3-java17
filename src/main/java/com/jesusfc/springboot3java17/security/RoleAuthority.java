package com.jesusfc.springboot3java17.security;

import com.jesusfc.springboot3java17.database.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * @author jesusfc
 * Created on mar 2023
 */
public class RoleAuthority implements GrantedAuthority {
    Set<RoleEntity> roles;
    public RoleAuthority(Set<RoleEntity> roles ) {
        this.roles = roles;
    }
    @Override
    public String getAuthority() {
        StringBuilder stringBuilder = new StringBuilder();
        roles.forEach(roleEntity -> stringBuilder.append(roleEntity));
        return stringBuilder.toString();
    }
}
