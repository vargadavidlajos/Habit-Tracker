package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.repository.UserRepository;
import com.example.habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
