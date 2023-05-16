package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.jesusfc.springboot3java17.model.Constant.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users") // En plural, las tablas en plural
@Entity
public class UserEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_video_clubs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private List<VideoClubEntity> videoClubs;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_films_rented",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<FilmEntity> filmRented;

    @Override
    public UserEntity clone() {
        try {
            UserEntity clone = (UserEntity) super.clone();
            clone.setVideoClubs(clone.getVideoClubs().stream().map(VideoClubEntity::clone).collect(Collectors.toList()));
            clone.setRoles(clone.getRoles().stream().map(RoleEntity::clone).collect(Collectors.toList()));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
