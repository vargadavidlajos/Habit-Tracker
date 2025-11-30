package com.example.habit_tracker.service;

import com.example.habit_tracker.entity.CompletionEntity;

import java.util.List;

public interface CompletionService {

    CompletionEntity markHabitCompleted(Long habitId);
    List<CompletionEntity> getCompletionsForHabit(Long habitId);
}
