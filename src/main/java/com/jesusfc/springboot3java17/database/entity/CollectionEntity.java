package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

import static com.jesusfc.springboot3java17.model.Constant.*;

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
@Table(name = "collections") // En plural, las tablas en plural
@Entity
public class CollectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = GENERAL_TITLE_MIN_CHAR, max = GENERAL_TITLE_MAX_CHAR, message = GENERAL_TITLE_CHAR_LENGTH)
    private String title;

    /*
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "collections_films",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private List<FilmEntity> filmList;
*/
    @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<CollectionFilmEntity> filmList;
}
