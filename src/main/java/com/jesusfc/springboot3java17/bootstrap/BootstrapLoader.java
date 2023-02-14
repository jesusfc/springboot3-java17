package com.jesusfc.springboot3java17.bootstrap;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public BootstrapLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args){
        loadBasics();
    }

    private void loadBasics() {
        if (userRepository.count() == 0) {
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(UserEntity.builder()
                    .email("jfcaraballo@gmail.com")
                    .enabled(true)
                    .name("Jesús")
                    .password("$2a$10$giokU5/.OtZE/G5y.1z4FOab7kmAoL2c0L/RCYok4r.xmL129GpgS").build());
            userEntities.add(UserEntity.builder()
                    .email("jesus.fdez.caraballo@gmail.com")
                    .enabled(true)
                    .name("JesúsFdez.")
                    .password("$2a$10$giokU5/.OtZE/G5y.1z4FOab7kmAoL2c0L/RCYok4r.xmL129GpgS").build());
            userRepository.saveAll(userEntities);
        }
    }
}
