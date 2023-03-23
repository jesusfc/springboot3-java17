package com.jesusfc.springboot3java17.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Component
public class TestFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * En SecurityConfig
         * .addFilterBefore(new TestFilter(), JWTAuthenticationFilter.class)
         * .addFilter(jwtAuthenticationFilter)
         * De esta forma se ejecutará el SOP antes, luego se comprobará la seguridad JWTAuth.. y
         * al final se ejecutará el SOP despues
         */
        System.out.println("Antes -- Hacemos doFilter() en TestFilter!!!!!!!!");
        chain.doFilter(request, response);
        System.out.println("Después -- Hacemos doFilter() en TestFilter!!!!!!!!");
    }
}
