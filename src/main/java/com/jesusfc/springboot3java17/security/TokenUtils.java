package com.jesusfc.springboot3java17.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class TokenUtils {

    /**
     * TODO mover a application.yml
     * Nos servir para firmar el token
     */
    private final static String ACCESS_TOKEN_SECRET = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY3NjM3NjM2OCwiaWF0IjoxNjc2Mzc2MzY4fQ.2-0PYBRcZS8Lb91U5wNcKvn6y_WAdL2sL5ScxabE-S0";

    /**
     * 30 Dias de validez en segundos
     */
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public static String createToken(UserDetails userDetails) {

        // Expiration Date
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000; // in milliseconds
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        // Roles
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        Claims claims = Jwts.claims();
        try {
            claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .addClaims(claims)
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
