package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.FilmEntity;
import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.entity.UserFilmRentedEntity;
import com.jesusfc.springboot3java17.database.repository.FilmRepository;
import com.jesusfc.springboot3java17.database.repository.UserFilmRentedRepository;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Service
@Transactional
@AllArgsConstructor
public class FilmServiceImpl implements FilmService{
    private final UserFilmRentedRepository userFilmRentedRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    @Override
    public List<FilmEntity> getFilmByVideoClubId(long videoClubId) {
        return filmRepository.findByVideoClubId(videoClubId);
    }

    @Override
    public List<UserFilmRentedEntity> getFilmRentedByUserId(String userEmail, String videoClubCode) {
        Optional<UserEntity> userForLogin = userRepository.findUserForLogin(userEmail, videoClubCode);
        if (userForLogin.isEmpty()) throw new RuntimeException("User NOT fount for this club");
        return userFilmRentedRepository.findByUserId(userForLogin.get().getId());
    }
}
