package com.jesusfc.springboot3java17.controller;


import com.jesusfc.springboot3java17.model.httpRQRS.LoginRQ;
import com.jesusfc.springboot3java17.model.httpRQRS.LoginRS;
import com.jesusfc.springboot3java17.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthController {

private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<LoginRS> login(@RequestBody LoginRQ loginRQ) {
        LoginRS loginRS = authService.loginUser(loginRQ);
        return new ResponseEntity<>(loginRS, HttpStatus.OK);
    }
}
