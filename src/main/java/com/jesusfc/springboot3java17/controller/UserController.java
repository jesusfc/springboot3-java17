package com.jesusfc.springboot3java17.controller;


import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.model.httpRQRS.UserEntityListRS;
import com.jesusfc.springboot3java17.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users-list")
    public ResponseEntity<UserEntityListRS> getUserList() {
        List<UserEntity> userList = userService.getUserList();
        return new ResponseEntity<>(UserEntityListRS.userEntityListRS(userList), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        Optional<UserEntity> byEmail = userService.getUserByEmail(email);
        if (byEmail.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(byEmail.get(), HttpStatus.OK);
    }
}
