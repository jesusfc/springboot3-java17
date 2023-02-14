package com.jesusfc.springboot3java17.security;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userByEmail = userService.getUserByEmail(email);
        if (userByEmail == null) throw new UsernameNotFoundException("The user email " + email + " has not been found.");
        return new UserDetailsImpl(userByEmail);
    }
}
