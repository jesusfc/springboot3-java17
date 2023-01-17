package com.jesusfc.springboot3java17.controller;

import com.jesusfc.springboot3java17.configuration.YAMLConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final YAMLConfig yamlConfig;

    public UserController(YAMLConfig yamlConfig) {
        this.yamlConfig = yamlConfig;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println(yamlConfig.toString());
        return String.format("Hello %s!", name);
    }

    @GetMapping("/user")
    public Integer getUserById(@RequestParam(value = "userId") int userId) {
        return userId;
    }
}
