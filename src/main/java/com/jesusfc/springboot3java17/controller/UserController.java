package com.jesusfc.springboot3java17.controller;


import com.jesusfc.springboot3java17.configuration.YAMLConfig;
import com.jesusfc.springboot3java17.model.UserEntity;
import com.jesusfc.springboot3java17.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final YAMLConfig yamlConfig;
    private final UserRepository userRepository;

    public UserController(YAMLConfig yamlConfig, UserRepository userRepository) {
        this.yamlConfig = yamlConfig;
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println(yamlConfig.toString());
        System.out.println(yamlConfig.getDefaultPropertyStyle().toString());
        return String.format("Hello %s!", name);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        Optional<UserEntity> byId = userRepository.findById(email);
        return byId.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
