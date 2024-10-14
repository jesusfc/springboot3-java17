package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Embeddable
public class CollectionFilmKey implements Serializable {

    @JoinColumn(name = "collection_id")
    private Long collectionId;

    @JoinColumn(name = "film_id")
    private Long filmId;
}
