package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.repository.UserRepository;
import com.example.habit_tracker.service.UserService;
import com.example.habit_tracker.service.dto.UserCreateDto;
import com.example.habit_tracker.service.dto.UserDto;
import com.example.habit_tracker.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.entityToDto(user);
    }

    @Override
    public UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        UserEntity userEntity = userMapper.createDtoToEntity(userCreateDto);

        userEntity.setHash(passwordEncoder.encode(userEntity.getHash()));

        UserEntity createdUser = userRepository.save(userEntity);
        return userMapper.entityToDto(createdUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
