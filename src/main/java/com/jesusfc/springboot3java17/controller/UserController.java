package com.jesusfc.springboot3java17.controller;


import com.jesusfc.springboot3java17.entity.UserEntity;
import com.jesusfc.springboot3java17.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users-list")
    public ResponseEntity<UserEntity> getUserList() {
        List<UserEntity> userList = userService.getUserList();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        UserEntity byEmail = userService.getUserByEmail(email);
        if (byEmail == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(byEmail, HttpStatus.OK);
    }
}
