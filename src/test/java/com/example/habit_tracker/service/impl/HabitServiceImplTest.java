package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.repository.HabitRepository;
import com.example.habit_tracker.service.UserService;
import com.example.habit_tracker.service.dto.HabitCreateDto;
import com.example.habit_tracker.service.dto.HabitDto;
import com.example.habit_tracker.service.mapper.HabitMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HabitServiceImplTest {

    @Mock
    private HabitRepository habitRepository;

    @Mock
    private HabitMapper habitMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private HabitServiceImpl habitService;

    @Test
    void testCreateHabit() {
        HabitCreateDto createDto = new HabitCreateDto();
        createDto.setHabitName("Exercise");
        createDto.setHabitDescription("Daily jogging");

        HabitEntity entity = new HabitEntity();
        HabitEntity savedEntity = new HabitEntity();
        savedEntity.setId(1L);
        savedEntity.setHabitName("Exercise");

        HabitDto dto = new HabitDto();
        dto.setId(1L);
        dto.setHabitName("Exercise");

        UserEntity user = new UserEntity();
        user.setId(42L);
        user.setUsername("john");

        when(userService.getUserEntityById(42L)).thenReturn(user);
        when(habitMapper.createDtoToEntity(createDto)).thenReturn(entity);
        when(habitRepository.save(entity)).thenReturn(savedEntity);
        when(habitMapper.entityToDto(savedEntity)).thenReturn(dto);

        HabitDto result = habitService.createHabit(createDto, 42L);

        assertEquals(1L, result.getId());
        assertEquals("Exercise", result.getHabitName());
        verify(habitRepository).save(entity);
    }

    @Test
    void testGetHabitById_found() {
        HabitEntity entity = new HabitEntity();
        entity.setId(1L);
        entity.setHabitName("Read");

        HabitDto dto = new HabitDto();
        dto.setId(1L);
        dto.setHabitName("Read");

        when(habitRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(habitMapper.entityToDto(entity)).thenReturn(dto);

        HabitDto result = habitService.getHabitById(1L);

        assertEquals("Read", result.getHabitName());
        verify(habitRepository).findById(1L);
    }

    @Test
    void testGetHabitById_notFound() {
        when(habitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> habitService.getHabitById(1L));
        verify(habitRepository).findById(1L);
    }

    @Test
    void testGetHabitEntityById_found() {
        HabitEntity entity = new HabitEntity();
        entity.setId(1L);
        entity.setHabitName("Read");

        when(habitRepository.findById(1L)).thenReturn(Optional.of(entity));

        HabitEntity result = habitService.getHabitEntityById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Read", result.getHabitName());
        verify(habitRepository).findById(1L);
    }

    @Test
    void testGetHabitEntityById_notFound() {
        when(habitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> habitService.getHabitEntityById(1L));
        verify(habitRepository).findById(1L);
    }

    @Test
    void testGetHabitsByUserId() {
        Long userId = 42L;

        HabitEntity h1 = new HabitEntity();
        h1.setId(1L);
        h1.setHabitName("Exercise");

        HabitEntity h2 = new HabitEntity();
        h2.setId(2L);
        h2.setHabitName("Read");

        HabitDto dto1 = new HabitDto();
        dto1.setId(1L);
        dto1.setHabitName("Exercise");
        dto1.setUserId(userId);

        HabitDto dto2 = new HabitDto();
        dto2.setId(2L);
        dto2.setHabitName("Read");
        dto2.setUserId(userId);

        when(habitRepository.findByUserId(userId)).thenReturn(List.of(h1, h2));

        when(habitMapper.entityToDto(h1)).thenReturn(dto1);
        when(habitMapper.entityToDto(h2)).thenReturn(dto2);

        List<HabitDto> result = habitService.getHabitsByUserId(userId);

        assertEquals(2, result.size());
        assertEquals("Exercise", result.get(0).getHabitName());
        assertEquals("Read", result.get(1).getHabitName());

        verify(habitRepository).findByUserId(userId);
        verify(habitMapper).entityToDto(h1);
        verify(habitMapper).entityToDto(h2);
    }

    @Test
    void testDeleteHabit() {
        HabitEntity entity = new HabitEntity();
        entity.setId(1L);

        when(habitRepository.findById(1L)).thenReturn(Optional.of(entity));

        habitService.deleteById(1L);

        verify(habitRepository).delete(entity);
    }

    @Test
    void testDeleteHabit_notFound() {
        when(habitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> habitService.deleteById(1L));
        verify(habitRepository).findById(1L);
    }
}
