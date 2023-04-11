package com.jesusfc.springboot3java17.security.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

/**
 * @author jesusfc
 * Created on abr 2023
 */
public interface JWTService {

    String createToken(Authentication auth) throws IOException;

    boolean validateToken(String token);

    Claims getClaims(String token);

    String getUsername(String token);

    Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;

    String resolveToken(String token);
}
