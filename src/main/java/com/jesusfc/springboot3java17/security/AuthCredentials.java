package com.jesusfc.springboot3java17.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
