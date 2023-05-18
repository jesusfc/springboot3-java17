package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.FilmEntity;
import com.jesusfc.springboot3java17.database.entity.UserFilmRentedEntity;

import java.util.List;

/**
 * @author jesusfc
 * Created on may 2023
 */
public interface FilmService {
    List<FilmEntity> getFilmByVideoClubId(long videoClubId);
    List<UserFilmRentedEntity> getFilmRentedByUserId(String userEmail, String videoClubCode);
}
