package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "collections_films") // En plural, las tablas en plural
@Entity
public class CollectionFilmEntity {


    @EmbeddedId
    CollectionFilmKey id;

    @ManyToOne
    @MapsId("collectionId")
    @JoinColumn(name = "collection_id")
    CollectionEntity collection;

    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "film_id")
    FilmEntity film;

    @Column(name = "idx")
    private Long idx;

}
