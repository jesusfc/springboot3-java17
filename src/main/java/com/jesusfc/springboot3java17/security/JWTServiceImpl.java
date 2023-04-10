package com.jesusfc.springboot3java17.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author jesusfc
 * Created on abr 2023
 */
@Service
public class JWTServiceImpl implements JWTService {

    public static final String ACCESS_TOKEN_SECRET = Base64Utils.encodeToString("Mi.Super.Clave.Secreta".getBytes());

    public static final long EXPIRATION_DATE = 14000000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Override
    public String createToken(Authentication auth) throws JsonProcessingException {

        // Expiration Date
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_DATE);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        // Roles
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .addClaims(claims)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            getClaims(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(resolveToken(token))
                .getBody();
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {

        Object roles = getClaims(token).get("authorities");

        return Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

    }

    @Override
    public String resolveToken(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}
