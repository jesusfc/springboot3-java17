package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.model.httpRQRS.LoginRQ;
import com.jesusfc.springboot3java17.model.httpRQRS.LoginRS;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public LoginRS loginUser(LoginRQ loginRQ) {
        return null;
    }
}
