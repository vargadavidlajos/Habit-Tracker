package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.repository.UserRepository;
import com.example.habit_tracker.service.dto.UserCreateDto;
import com.example.habit_tracker.service.dto.UserDto;
import com.example.habit_tracker.service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateUser() {

        UserCreateDto dto = new UserCreateDto();
        dto.setUsername("john");
        dto.setPassword("pass123");

        UserEntity entityToSave = new UserEntity();
        entityToSave.setUsername("john");
        entityToSave.setHash("encoded-pass");

        UserEntity savedEntity = new UserEntity();
        savedEntity.setId(1L);
        savedEntity.setUsername("john");
        savedEntity.setHash("encoded-pass");

        UserDto expectedDto = new UserDto();
        expectedDto.setId(1L);
        expectedDto.setUsername("john");

        when(passwordEncoder.encode("pass123")).thenReturn("encoded-pass");
        when(userMapper.createDtoToEntity(dto)).thenReturn(entityToSave);
        when(userRepository.save(entityToSave)).thenReturn(savedEntity);
        when(userMapper.entityToDto(savedEntity)).thenReturn(expectedDto);

        UserDto result = userService.createUser(dto);

        assertEquals(1L, result.getId());
        assertEquals("john", result.getUsername());

        verify(passwordEncoder).encode("pass123");
        verify(userRepository).save(entityToSave);
    }

    @Test
    void testGetUserById_found() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUsername("john");

        UserDto johnUserDto = new UserDto();
        johnUserDto.setId(1L);
        johnUserDto.setUsername("john");

        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userMapper.entityToDto(entity)).thenReturn(johnUserDto);

        UserDto result = userService.getUserById(1L);

        assertEquals(1L, result.getId());
        assertEquals("john", result.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserEntityById_found() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("john");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserEntity result = userService.getUserEntityById(1L);

        assertEquals(1L, result.getId());
        assertEquals("john", result.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserEntityById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.getUserEntityById(1L));
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserByUsername() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("john");

        UserDto dto = new UserDto();
        dto.setId(1L);
        dto.setUsername("john");

        when(userRepository.findByUsername("john")).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(dto);

        UserDto result = userService.getUserByUsername("john");

        assertEquals(1L, result.getId());
        assertEquals("john", result.getUsername());

        verify(userRepository).findByUsername("john");
        verify(userMapper).entityToDto(user);
    }

    @Test
    void testDeleteUser_found() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository).findById(1L);
        verify(userRepository).delete(user);
    }

    @Test
    void testDeleteUser_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(1L));

        verify(userRepository).findById(1L);
        verify(userRepository, never()).delete(any());
    }
}
