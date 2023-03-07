package com.jesusfc.springboot3java17.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.model.converter.UserConverter;
import com.jesusfc.springboot3java17.openApi.v1.api.IUser;
import com.jesusfc.springboot3java17.openApi.v1.model.User;
import com.jesusfc.springboot3java17.openApi.v1.model.UserPageList;
import com.jesusfc.springboot3java17.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/rest/")
public class UserRestController implements IUser {

    private final UserService userService;
    private final LocaleResolver localeResolver;

    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;


    @Override
    public ResponseEntity<Void> createUser(User body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delUserById(UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        return new ResponseEntity<>(new UserConverter().convert(userService.getUserByEmail(email)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> getUserList(Locale locale) {
        String message = messageSource.getMessage("app.name", null, locale);
        log.debug("message: " + message);
        log.error("message: " + message);
        List<UserEntity> userList = userService.getUserList();
        return new ResponseEntity<>(new UserConverter().convertList(userList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserPageList>> getUserPageList(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUserById(UUID userId, User body) {
        return null;
    }
}
