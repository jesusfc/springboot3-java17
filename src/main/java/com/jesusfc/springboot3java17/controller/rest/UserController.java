package com.jesusfc.springboot3java17.controller.rest;


import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.model.httpRQRS.UserEntityListRS;
import com.jesusfc.springboot3java17.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final LocaleResolver localeResolver;
    private final MessageSource messageSource;

    @GetMapping("/list")
    public ResponseEntity<UserEntityListRS> getUserList(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        System.out.println("message: " + message);
        List<UserEntity> userList = userService.getUserList();
        return new ResponseEntity<>(UserEntityListRS.userEntityListRS(userList), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request, HttpServletResponse httpResponse, @RequestParam(value = "name", defaultValue = "World") String name) {

        localeResolver.setLocale(request, httpResponse, new Locale("en", "EN"));


        return String.format("Hello %s!", name);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        Optional<UserEntity> byEmail = userService.getUserByEmail(email);
        if (byEmail.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(byEmail.get(), HttpStatus.OK);
    }
}
