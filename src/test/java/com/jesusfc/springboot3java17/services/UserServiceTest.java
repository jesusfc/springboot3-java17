package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should return an empty page when there are no users")
    void getUserPageListWhenNoUsersThenReturnEmptyPage() {
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> emptyPage = new PageImpl<>(new ArrayList<>(), pageable, 0);

        when(userRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<UserEntity> result = userService.getUserPageList(pageNumber, pageSize);

        assertEquals(emptyPage, result);
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Should return the first page when the page number is less than or equal to zero")
    void getUserPageListWhenPageNumberIsLessThanOrEqualToZeroThenReturnFirstPage() {
        int pageNumber = -1;
        int pageSize = 5;
        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            users.add(
                    UserEntity.builder()
                            .id((long) i)
                            .name("Name" + i)
                            .familyName("FamilyName" + i)
                            .email("email" + i + "@example.com")
                            .password("password" + i)
                            .enabled(true)
                            .createAt(LocalDateTime.now())
                            .build());
        }
        Page<UserEntity> expectedPage =
                new PageImpl<>(users, PageRequest.of(0, pageSize), users.size());
        when(userRepository.findAll(PageRequest.of(0, pageSize))).thenReturn(expectedPage);

        Page<UserEntity> actualPage = userService.getUserPageList(pageNumber, pageSize);

        assertEquals(expectedPage, actualPage);
        verify(userRepository, times(1)).findAll(PageRequest.of(0, pageSize));
    }

    @Test
    @DisplayName("Should return a page of users with the given page number and page size")
    void getUserPageListWithValidPageNumberAndPageSize() {
        int pageNumber = 1;
        int pageSize = 5;
        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            users.add(
                    UserEntity.builder()
                            .id((long) i)
                            .name("Name" + i)
                            .familyName("FamilyName" + i)
                            .email("email" + i + "@example.com")
                            .password("password" + i)
                            .enabled(true)
                            .createAt(LocalDateTime.now())
                            .build());
        }
        Page<UserEntity> userPage =
                new PageImpl<>(users, PageRequest.of(pageNumber, pageSize), users.size());
        when(userRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(userPage);

        Page<UserEntity> result = userService.getUserPageList(pageNumber, pageSize);

        assertEquals(userPage, result, "The returned page should match the expected page");
        verify(userRepository, times(1)).findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Test
    @DisplayName(
            "Should return a page with the default page size when the page size is less than or equal to zero")
    void getUserPageListWhenPageSizeIsLessThanOrEqualToZeroThenReturnDefaultPageSize() {
        int pageNumber = 1;
        int pageSize = 0;
        int defaultPageSize = 10;

        List<UserEntity> users = new ArrayList<>();
        for (int i = 0; i < defaultPageSize; i++) {
            users.add(
                    UserEntity.builder()
                            .id((long) i)
                            .name("Name" + i)
                            .familyName("FamilyName" + i)
                            .email("email" + i + "@example.com")
                            .password("password" + i)
                            .enabled(true)
                            .createAt(LocalDateTime.now())
                            .build());
        }

        Page<UserEntity> userPage =
                new PageImpl<>(users, PageRequest.of(pageNumber, defaultPageSize), users.size());

        when(userRepository.findAll(PageRequest.of(pageNumber, defaultPageSize)))
                .thenReturn(userPage);

        Page<UserEntity> result = userService.getUserPageList(pageNumber, pageSize);

        assertEquals(userPage, result);
        verify(userRepository, times(1)).findAll(PageRequest.of(pageNumber, defaultPageSize));
    }

    @Test
    @DisplayName(
            "Should return the last page when the page number is greater than the total number of pages")
    void getUserPageListWhenPageNumberIsGreaterThanTotalPagesThenReturnLastPage() {
        int pageNumber = 5;
        int pageSize = 2;
        List<UserEntity> users = new ArrayList<>();
        users.add(
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .build());
        users.add(
                UserEntity.builder()
                        .id(2L)
                        .name("Jane")
                        .familyName("Doe")
                        .email("jane.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .build());

        Page<UserEntity> expectedPage =
                new PageImpl<>(users, PageRequest.of(pageNumber, pageSize), users.size());
        when(userRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(expectedPage);

        Page<UserEntity> actualPage = userService.getUserPageList(pageNumber, pageSize);

        assertEquals(expectedPage, actualPage, "The returned page should be the last page");
        verify(userRepository, times(1)).findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Test
    @DisplayName("Should return null when the email is invalid")
    void getUserLoginWhenEmailIsInvalid() {
        String email = "invalid.email";
        String clubCode = "clubCode";

        when(userRepository.findByEmail(email)).thenReturn(null);

        UserEntity result = userService.getUserLogin(email, clubCode);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return null when both email and clubCode are invalid")
    void getUserLoginWhenEmailAndClubCodeAreInvalid() {
        String email = "invalid@example.com";
        String clubCode = "invalidClubCode";
        when(userRepository.findByEmail(email)).thenReturn(null);

        UserEntity result = userService.getUserLogin(email, clubCode);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return null when the clubCode is invalid")
    void getUserLoginWhenClubCodeIsInvalid() {
        String email = "test@example.com";
        String clubCode = "invalidClubCode";
        UserEntity userEntity =
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email(email)
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .videoClubs(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(userEntity));

        UserEntity result = userService.getUserLogin(email, clubCode);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return the user when the email and clubCode are valid")
    void getUserLoginWhenEmailAndClubCodeAreValid() {
        String email = "test@example.com";
        String clubCode = "CLUB01";
        UserEntity expectedUser =
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email(email)
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .videoClubs(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(expectedUser));

        UserEntity actualUser = userService.getUserLogin(email, clubCode);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should not save the user and throw an exception when user entity is null")
    void saveUserWhenUserEntityIsNullThenThrowException() {
        UserEntity userEntity = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> userService.saveUser(userEntity));

        // Verify
        verify(userRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should save the user and return the saved user entity")
    void saveUserAndReturnSavedUserEntity() {
        UserEntity userEntity =
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .videoClubs(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();

        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserEntity savedUserEntity = userService.saveUser(userEntity);

        assertEquals(userEntity, savedUserEntity);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    @DisplayName("Should return null when the email does not exist")
    void getUserByEmailWhenEmailDoesNotExist() {
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        UserEntity result = userService.getUserByEmail(email);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return the user when the email exists")
    void getUserByEmailWhenEmailExists() {
        String email = "test@example.com";
        UserEntity expectedUser =
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email(email)
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .videoClubs(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(expectedUser));

        UserEntity actualUser = userService.getUserByEmail(email);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Should return a list of all users")
    void getUserList() {
        List<UserEntity> expectedUserList = new ArrayList<>();
        expectedUserList.add(
                UserEntity.builder()
                        .id(1L)
                        .name("John")
                        .familyName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .build());
        expectedUserList.add(
                UserEntity.builder()
                        .id(2L)
                        .name("Jane")
                        .familyName("Doe")
                        .email("jane.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .build());

        when(userRepository.findAll()).thenReturn(expectedUserList);

        List<UserEntity> actualUserList = userService.getUserList();

        assertEquals(
                expectedUserList,
                actualUserList,
                "The returned user list should match the expected user list");
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should delete the user by ID when the user exists")
    void deleteUserByIdWhenUserExists() {
        long userId = 1L;

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    @DisplayName("Should throw an exception when the user ID is not found")
    void getUserByIdWhenUserIdNotFoundThenThrowException() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ClassNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Should return the user when the user ID is valid")
    void getUserByIdWhenUserIdIsValid() {
        Long userId = 1L;
        UserEntity expectedUser =
                UserEntity.builder()
                        .id(userId)
                        .name("John")
                        .familyName("Doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .enabled(true)
                        .createAt(LocalDateTime.now())
                        .videoClubs(new ArrayList<>())
                        .roles(new ArrayList<>())
                        .build();

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(expectedUser));

        UserEntity actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
    }
}