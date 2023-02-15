package com.jesusfc.springboot3java17.model.httpRQRS;

import lombok.Data;

@Data
public class LoginRS {
    private String userName;
    private String email;
    private String token;
}
