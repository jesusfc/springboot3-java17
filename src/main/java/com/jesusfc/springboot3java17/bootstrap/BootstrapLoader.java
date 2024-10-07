package com.jesusfc.springboot3java17.bootstrap;

import com.jesusfc.springboot3java17.database.entity.*;
import com.jesusfc.springboot3java17.database.repository.*;
import com.jesusfc.springboot3java17.security.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BootstrapLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VideoClubRepository videoClubRepository;
    private final RoleRepository roleRepository;
    private final FilmRepository filmRepository;
    private final UserFilmRentedRepository userFilmRentedRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionFilmRepository collectionFilmRepository;

    @Override
    public void run(String... args) {
        loadBasics();
    }

    private void loadBasics() {
        if (videoClubRepository.count() == 0) {
            List<VideoClubEntity> videoClubEntityList = new ArrayList<>();
            videoClubEntityList.add(VideoClubEntity.builder()
                    .code("ALC")
                    .name("Video Club Alcudia")
                    .description("Club situado en la localidad de Alcudia en el norte de Mallorca")
                    .build());
            videoClubEntityList.add(VideoClubEntity.builder()
                    .code("ALP")
                    .name("Video Club Puerto de Alcudia")
                    .description("Club situado en el Puerto de Alcudia, una zona fantastica")
                    .build());
            videoClubRepository.saveAll(videoClubEntityList);
        }
        if (userRepository.count() == 0) {
            List<VideoClubEntity> videoClubEntities1 = new ArrayList<>();
            videoClubEntities1.add(videoClubRepository.findByCode("ALC").get());
            videoClubEntities1.add(videoClubRepository.findByCode("ALP").get());
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(UserEntity.builder()
                    .email("jfcaraballo@gmail.com")
                    .enabled(true)
                    .name("Jesús")
                    .familyName("Fdez.")
                    .videoClubs(videoClubEntities1)
                    .createAt(LocalDateTime.now())
                    .password("$2a$10$giokU5/.OtZE/G5y.1z4FOab7kmAoL2c0L/RCYok4r.xmL129GpgS").build());

            List<VideoClubEntity> videoClubEntities2 = new ArrayList<>();
            videoClubEntities2.add(videoClubRepository.findByCode("ALC").get());
            userEntities.add(UserEntity.builder()
                    .email("jesus.fdez.caraballo@gmail.com")
                    .enabled(true)
                    .name("Abel")
                    .familyName("Vives")
                    .createAt(LocalDateTime.now())
                    .password("$2a$10$giokU5/.OtZE/G5y.1z4FOab7kmAoL2c0L/RCYok4r.xmL129GpgS")
                    .videoClubs(videoClubEntities2)
                    .build());
            userRepository.saveAll(userEntities);

            roleRepository.save(new RoleEntity().builder()
                    .userId(userRepository.findByEmail("jfcaraballo@gmail.com").get().getId())
                    .roles(RolesEnum.ROLE_USER)
                    .build());
            roleRepository.save(new RoleEntity().builder()
                    .userId(userRepository.findByEmail("jfcaraballo@gmail.com").get().getId())
                    .roles(RolesEnum.ROLE_ADMIN)
                    .build());
            roleRepository.save(new RoleEntity().builder()
                    .userId(userRepository.findByEmail("jesus.fdez.caraballo@gmail.com").get().getId())
                    .roles(RolesEnum.ROLE_USER)
                    .build());
        }
        if (filmRepository.count() == 0) {
            filmRepository.save(FilmEntity.builder()
                    .title("Capitan America")
                    .filmCode("CAP_AMER")
                    .videoClub(videoClubRepository.findByCode("ALC").get())
                    .build());
            /*
            filmRepository.save(FilmEntity.builder()
                    .title("Infinity")
                    .filmCode("INFINITY")
                    .videoClub(videoClubRepository.findByCode("ALC").get())
                    .build());

             */
            filmRepository.save(FilmEntity.builder()
                    .title("Capitan America 2")
                    .filmCode("CAP_AMER_2")
                    .videoClub(videoClubRepository.findByCode("ALP").get())
                    .build());
            /*
            filmRepository.save(FilmEntity.builder()
                    .title("Star Wars")
                    .filmCode("STAR_WARS")
                    .videoClub(videoClubRepository.findByCode("ALP").get())
                    .build());

             */
        }
        if (userFilmRentedRepository.count() == 0) {

            userFilmRentedRepository.save(UserFilmRentedEntity.builder()
                    .user(userRepository.findByEmail("jfcaraballo@gmail.com").get())
                    .film(filmRepository.findById(1L).get())
                    .rentedOn(LocalDateTime.now())
                    .build());
            /*
            userFilmRentedRepository.save(UserFilmRentedEntity.builder()
                    .user(userRepository.findByEmail("jfcaraballo@gmail.com").get())
                    .film(filmRepository.findById(2L).get())
                    .rentedOn(LocalDateTime.now())
                    .build());

             */
        }
        if (collectionRepository.count() == 0) {
            filmRepository.getReferenceById(1L);
            collectionRepository.save(CollectionEntity.builder().title("Aventuras").build());
            collectionRepository.save(CollectionEntity.builder().title("Acción").build());
        }
/*
        if (collectionFilmRepository.count() == 0) {

            collectionFilmRepository.save(CollectionFilmEntity.builder()
                    .id(CollectionFilmKey.builder().collectionId(1L).filmId(1L).build())
                    .collection(collectionRepository.getReferenceById(1L))
                    .film(filmRepository.getReferenceById(1L))
                    .idx(1L).build());
            collectionFilmRepository.save(CollectionFilmEntity.builder()
                    .id(CollectionFilmKey.builder().collectionId(1L).filmId(2L).build())
                    .collection(collectionRepository.getReferenceById(1L))
                    .film(filmRepository.getReferenceById(2L))
                    .idx(2L).build());
        }
 */

    }
}
