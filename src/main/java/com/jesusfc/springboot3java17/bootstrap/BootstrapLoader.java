package com.jesusfc.springboot3java17.bootstrap;

import com.jesusfc.springboot3java17.database.entity.RoleEntity;
import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.entity.VideoClubEntity;
import com.jesusfc.springboot3java17.database.repository.RoleRepository;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import com.jesusfc.springboot3java17.database.repository.VideoClubRepository;
import com.jesusfc.springboot3java17.security.RolesEnum;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class BootstrapLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VideoClubRepository videoClubRepository;
    private final RoleRepository roleRepository;

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
            Set<VideoClubEntity> videoClubEntities1 = new HashSet<>();
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

            Set<VideoClubEntity> videoClubEntities2 = new HashSet<>();
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
    }
}
