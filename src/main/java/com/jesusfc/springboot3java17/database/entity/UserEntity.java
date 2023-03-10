package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.jesusfc.springboot3java17.model.Constant.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users") // En plural, las tablas en plural
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;
    @Column(name = "family_name", nullable = false, length = 15)
    private String familyName;

    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @Size(max = EMAIL_MAX_CHAR, message = EMAIL_CHAR_LENGTH)
    @Email(regexp = USERNAME_EMAIL_PATTERN, message = EMAIL_PATTERN_ERROR)
    @Column(name = "email", unique = true, nullable = false, length = EMAIL_MAX_CHAR)
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "create_at")
    private LocalDateTime createAt;

}
