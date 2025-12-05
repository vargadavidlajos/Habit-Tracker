package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.service.dto.UserCreateDto;
import com.example.habit_tracker.service.dto.UserDto;

public interface UserService {

    UserDto getUserById(Long id);
    UserEntity getUserEntityById(Long id);
    UserDto getUserByUsername(String username);
    UserDto createUser(UserCreateDto userCreateDto);
    void deleteUser(Long id);
}
