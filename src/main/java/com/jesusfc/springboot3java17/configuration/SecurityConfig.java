package com.jesusfc.springboot3java17.configuration;

import com.jesusfc.springboot3java17.security.JWTAuthenticationFilter;
import com.jesusfc.springboot3java17.security.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

    //    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
     //   jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
     //   jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .csrf().disable()
                /*.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()*/
                .httpBasic().disable()
                //.addFilter(jwtAuthenticationFilter)
                //.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

                /*
                  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()//enable cors
                .and()
                .csrf().disable()//disable csrf
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//stateless session (Rest)
                .authorizeHttpRequests(authz->authz
                        .antMatchers(HttpMethod.GET,"/").permitAll()
                        .antMatchers(HttpMethod.POST,"/users/login","users/register","users/forgot-password").permitAll()
                        .antMatchers(HttpMethod.PATCH,"/users/password").permitAll()
                        .anyRequest().authenticated());//authorize any request except ignored endpoint above
        return httpSecurity.build();
                 */

    }

    /* UserDetailsService para autentificación simbre con user/pass.
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles()
                        .build());
        return inMemoryUserDetailsManager;
    }
    */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

}
