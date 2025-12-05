package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.repository.HabitRepository;
import com.example.habit_tracker.service.HabitService;
import com.example.habit_tracker.service.UserService;
import com.example.habit_tracker.service.dto.HabitCreateDto;
import com.example.habit_tracker.service.dto.HabitDto;
import com.example.habit_tracker.service.mapper.HabitMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;
    private final UserService userService;

    @Override
    public HabitDto getHabitById(Long id) {
        HabitEntity habit = habitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found"));
        return habitMapper.entityToDto(habit);
    }

    public HabitEntity getHabitEntityById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found"));
    }

    @Override
    public void deleteById(Long id) {
        HabitEntity habit = habitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habit not found"));
        habitRepository.delete(habit);
    }

    @Override
    public List<HabitDto> getHabitsByUserId(Long id) {
        List<HabitEntity> habits = habitRepository.findByUserId(id);
        return habits.stream()
                .map(habitMapper::entityToDto)
                .toList();
    }

    @Override
    public HabitDto createHabit(HabitCreateDto habitCreateDto, Long userId) {
        HabitEntity habitEntity = habitMapper.createDtoToEntity(habitCreateDto);
        UserEntity userEntity = userService.getUserEntityById(userId);
        habitEntity.setUser(userEntity);
        return habitMapper.entityToDto(habitRepository.save(habitEntity));
    }
}
