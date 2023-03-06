package com.jesusfc.springboot3java17.controller.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusfc.springboot3java17.openApi.v1.api.IUser;
import com.jesusfc.springboot3java17.openApi.v1.model.UserList;
import com.jesusfc.springboot3java17.openApi.v1.model.UserPageList;
import com.jesusfc.springboot3java17.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
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
    public ResponseEntity<Void> createUser(IUser body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delUserById(UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<IUser> getUserById(UUID userId) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserList>> getUserList() {
        return null;
    }

    @Override
    public ResponseEntity<List<UserPageList>> getUserPageList(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUserById(UUID userId, IUser body) {
        return null;
    }
}
