package com.jesusfc.springboot3java17.services;

import com.jesusfc.springboot3java17.database.entity.UserEntity;
import com.jesusfc.springboot3java17.database.repository.UserRepository;
import com.jesusfc.springboot3java17.exception.UserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUserList() {
        List<UserEntity> all = userRepository.findAll();
        if (CollectionUtils.isEmpty(all)) return List.of();
        return all;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserLogin(String email, String clubCode) {
        Optional<UserEntity> byEmail = userRepository.findUserForLogin(email, clubCode);
        return byEmail.orElseThrow(() -> new UserException("Usuario NO encontrado!!!"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserByEmail(String email) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        return byEmail.orElseThrow(() -> new UserException("Usuario NO encontrado!!!"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException("Usuario NO encontrado!!!"));
    }

    @Override
    @Transactional()
    public UserEntity saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    @Transactional
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Page<UserEntity> getUserPageList(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }
}
