package com.jesusfc.springboot3java17.controller.rest;

import com.jesusfc.springboot3java17.database.entity.FilmEntity;
import com.jesusfc.springboot3java17.database.entity.UserFilmRentedEntity;
import com.jesusfc.springboot3java17.model.converter.FilmConverter;
import com.jesusfc.springboot3java17.openApi.v1.api.IFilmDocumentation;
import com.jesusfc.springboot3java17.openApi.v1.model.Film;
import com.jesusfc.springboot3java17.services.FilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * @author jesusfc
 * Created on may 2023
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/rest/")
public class FilmRestController implements IFilmDocumentation {

    private final FilmService filmService;

    @Override
    public ResponseEntity<List<Film>> getFilmList(Locale locale) {
        List<FilmEntity> filmByVideoClubId = filmService.getFilmByVideoClubId(1L);
        FilmConverter filmConverter = new FilmConverter();
        return new ResponseEntity<>(filmConverter.convertList(filmByVideoClubId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserFilmRentedEntity>> getFilmRentedByUserList(String userEmail, Locale locale) {
        List<UserFilmRentedEntity> alc = filmService.getFilmRentedByUserId("jfcaraballo@gmail.com", "ALC");
        return new ResponseEntity<>(alc, HttpStatus.OK);
    }
}
