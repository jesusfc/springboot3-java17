package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUserList() {
        List<UserEntity> all = userRepository.findAll();
        if(CollectionUtils.isEmpty(all)) return List.of();
        return all;
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findById(email);
    }
}
