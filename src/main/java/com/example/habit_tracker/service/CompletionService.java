package com.example.habit_tracker.service;

import com.example.habit_tracker.service.dto.CompletionDto;

import java.util.List;

public interface CompletionService {

    CompletionDto markHabitCompleted(Long habitId);
    List<CompletionDto> getCompletionsForHabit(Long habitId);
}
