package com.jesusfc.springboot3java17.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.jesusfc.springboot3java17.model.Constant.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "user")
public class UserEntity {
    @Id
    @Column(name = "email", unique = true, nullable = false, length = EMAIL_MAX_CHAR)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @Size(max = EMAIL_MAX_CHAR, message = EMAIL_CHAR_LENGTH)
    @Email(regexp = USERNAME_EMAIL_PATTERN, message = EMAIL_PATTERN_ERROR)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
}
