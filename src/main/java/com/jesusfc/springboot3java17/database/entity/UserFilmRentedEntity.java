package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users_films_rented") // En plural, las tablas en plural
@Entity
public class UserFilmRentedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rented_on")
    private LocalDateTime rentedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("id")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @ToString.Exclude
    //@MapsId("id")
    private FilmEntity film;

}
