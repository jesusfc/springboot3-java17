package com.jesusfc.springboot3java17.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.springboot3java17.security.AuthCredentials;
import com.jesusfc.springboot3java17.security.JWTService;
import com.jesusfc.springboot3java17.security.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTService jwtService;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.jwtService = jwtService;
        this.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl("/rest/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            logger.error(e);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail() + "#" + authCredentials.getClubCode(), authCredentials.getPassword());
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        // Add Token to the header
        String token = jwtService.createToken(authResult);
        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();

        Map<String, Object> user = new HashMap<>();
        user.put("enabled", userDetails.isEnabled());
        user.put("authorities", userDetails.getAuthorities());
        user.put("username", userDetails.getUsername());

        // Add more info to the body
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("user", user);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error de autenticación: username o password incorrecto!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType("application/json");
    }

}
