package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.service.dto.HabitCreateDto;
import com.example.habit_tracker.service.dto.HabitDto;

import java.util.List;

public interface HabitService {

    HabitDto getHabitById(Long id);
    HabitEntity getHabitEntityById(Long id);
    void deleteById(Long id);
    List<HabitDto> getHabitsByUserId(Long id);
    HabitDto createHabit(HabitCreateDto habitCreateDto, Long userId);
}
