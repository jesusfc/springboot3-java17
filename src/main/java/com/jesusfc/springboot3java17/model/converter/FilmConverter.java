package com.jesusfc.springboot3java17.model.converter;

import com.jesusfc.springboot3java17.database.entity.FilmEntity;
import com.jesusfc.springboot3java17.openApi.v1.model.Film;

import java.util.List;

/**
 * @author jesusfc
 * Created on may 2023
 */
public class FilmConverter implements ConverterAll<FilmEntity, Film> {


    @Override
    public Film convert(FilmEntity source) {
        VideoClubConverter videoClubConverter = new VideoClubConverter();
        Film film = new Film();
        film.setId(source.getId());
        film.setFilmCode(source.getFilmCode());
        film.setTitle(source.getTitle());
        film.setVideoClub(videoClubConverter.convert(source.getVideoClub()));
        return film;
    }

    @Override
    public List<Film> convertList(List<FilmEntity> sourceList) {
        return sourceList.stream().map(this::convert).toList();
    }

    @Override
    public FilmEntity convertSource(Film target) {
        return null;
    }

    @Override
    public List<FilmEntity> convertSourceList(List<Film> targetList) {
        return null;
    }
}
