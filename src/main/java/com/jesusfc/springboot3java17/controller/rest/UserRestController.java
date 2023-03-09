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
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Void> createUser(User user) {
        userService.saveUser(new UserConverter().convertSource(user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
    public ResponseEntity<UserPageList> getUserPageList(Integer pageNumber, Integer pageSize) {
        Page<UserEntity> userEntityPageList = userService.getUserPageList(pageNumber - 1, pageSize);
        if (userEntityPageList.getTotalElements() == 0) new ResponseEntity<>(HttpStatus.NO_CONTENT, HttpStatus.OK);
        List<User> users = new UserConverter().convertList(userEntityPageList.getContent());
        UserPageList userPageList = new UserPageList();
        userPageList.setTotalElements((int) userEntityPageList.getTotalElements());
        userPageList.setNumberOfElements(users.size());
        userPageList.setContent(users);
        int totalNumberPages = Math.round(userEntityPageList.getTotalElements() / pageSize);
        userPageList.setTotalPages( totalNumberPages == 0 ? 1 : totalNumberPages);
        return new ResponseEntity<>(userPageList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateUserById(UUID userId, User body) {
        return null;
    }
}
