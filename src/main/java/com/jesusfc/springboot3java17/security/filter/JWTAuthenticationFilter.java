package com.jesusfc.springboot3java17.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.springboot3java17.security.AuthCredentials;
import com.jesusfc.springboot3java17.security.TokenUtils;
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
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
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

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails);

        // Add Token to the header
        response.addHeader("Authorization", "Bearer " + token);

        Map<String, Object> user = new HashMap<>();
        user.put("enabled", userDetails.isEnabled());
        user.put("authorities", userDetails.getAuthorities());
        user.put("username", userDetails.getUsername());

        // Add more info to the body
        Map<String, Object> body = new HashMap<>();
        body.put("token", "Bearer " + token);
        body.put("user", user);
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");

    }

}
