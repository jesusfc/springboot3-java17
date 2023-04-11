package com.jesusfc.springboot3java17.security.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email_and_club) throws UsernameNotFoundException {
        String[] split = email_and_club.split("#");
        UserEntity userByEmail = userService.getUserLogin(split[0], split[1]);
        if(userByEmail == null) throw new UsernameNotFoundException("The user email " + split[0] + " has not been found.");
        return new UserDetailsImpl(userByEmail);
    }
}
