package com.jesusfc.springboot3java17.controller.rest;


import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.model.httpRQRS.UserEntityListRS;
import com.jesusfc.springboot3java17.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/rest/user")
public class UserRestController {

    private final UserService userService;
    private final LocaleResolver localeResolver;
    private final MessageSource messageSource;

    @Operation(operationId = "userList")
    @GetMapping("/list")
    public ResponseEntity<UserEntityListRS> getUserList(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        System.out.println("message: " + message);
        List<UserEntity> userList = userService.getUserList();
        return new ResponseEntity<>(UserEntityListRS.userEntityListRS(userList), HttpStatus.OK);
    }

    @GetMapping("/language-by-param")
    public String hello(HttpServletRequest request, HttpServletResponse httpResponse,
                        @RequestParam(value = "idioma", defaultValue = "es") String lang) {
        localeResolver.setLocale(request, httpResponse, new Locale(lang));
        return String.format("New Language: %s!", lang);
    }

    @GetMapping("/language-by-interceptor")
    public String hello(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        return String.format("Cambiamos el idioma atravez del interceptor: " + message);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable(value = "email") String email) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("app.name", null, locale);
        System.out.println("Lang: " + locale.getLanguage() + ", Message: " + message);
        Optional<UserEntity> byEmail = userService.getUserByEmail(email);
        if (byEmail.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(byEmail.get(), HttpStatus.OK);
    }
}
