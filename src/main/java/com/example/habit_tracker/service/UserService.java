package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity createUser(UserEntity userEntity);
    void deleteUser(Long id);
}
