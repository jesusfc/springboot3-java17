package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.jesusfc.springboot3java17.model.Constant.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "films") // En plural, las tablas en plural
@Entity
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = FILM_CODE_MIN_CHAR, max = FILM_CODE_MIN_CHAR, message = FILM_TITLE_CHAR_LENGTH)
    private String filmCode;

    @Size(min = FILM_TITLE_MIN_CHAR, max = FILM_TITLE_MAX_CHAR, message = FILM_TITLE_CHAR_LENGTH)
    private String title;

    @OneToOne
    private VideoClubEntity videoClub;

}
