package com.jesusfc.springboot3java17.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

import static com.jesusfc.springboot3java17.model.Constant.*;

@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "films")
@AllArgsConstructor // En plural, las tablas en plural
public class FilmEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = FILM_CODE_MIN_CHAR, max = FILM_CODE_MAX_CHAR, message = FILM_CODE_CHAR_LENGTH)
    private String filmCode;

    @Size(min = GENERAL_TITLE_MIN_CHAR, max = GENERAL_TITLE_MAX_CHAR, message = GENERAL_TITLE_CHAR_LENGTH)
    private String title;

    @OneToOne
    @JoinColumn(name = "video_club_id", referencedColumnName = "id")
    private VideoClubEntity videoClub;


    @Override
    public FilmEntity clone() {
        try {
            FilmEntity clone = (FilmEntity) super.clone();
            clone.setVideoClub(clone.getVideoClub().clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FilmEntity that = (FilmEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
