package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.repository.HabitRepository;
import com.example.habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Override
    public HabitEntity getHabitById(Long id) {
        return habitRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        HabitEntity habit = getHabitById(id);
        habitRepository.delete(habit);
    }

    @Override
    public List<HabitEntity> getHabitsByUserId(Long id) {
        return habitRepository.findByUserId(id);
    }

    @Override
    public HabitEntity createHabit(HabitEntity habitEntity) {
        return habitRepository.save(habitEntity);
    }
}
