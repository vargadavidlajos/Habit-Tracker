package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.HabitEntity;

import java.util.List;

public interface HabitService {

    HabitEntity getHabitById(Long id);
    void deleteById(Long id);
    List<HabitEntity> getHabitsByUserId(Long id);
    HabitEntity createHabit(HabitEntity habitEntity);
}
