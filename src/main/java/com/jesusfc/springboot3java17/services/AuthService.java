package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.model.httpRQRS.LoginRQ;
import com.jesusfc.springboot3java17.model.httpRQRS.LoginRS;

public interface AuthService {

    LoginRS loginUser(LoginRQ loginRQ);
}
