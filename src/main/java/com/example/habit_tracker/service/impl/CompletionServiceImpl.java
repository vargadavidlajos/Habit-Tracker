package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.CompletionEntity;
import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.repository.CompletionRepository;
import com.example.habit_tracker.service.CompletionService;
import com.example.habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletionServiceImpl implements CompletionService {

    private final CompletionRepository completionRepository;
    private final HabitService habitService;

    @Override
    public CompletionEntity markHabitCompleted(Long habitId) {
        HabitEntity habit = habitService.getHabitById(habitId);

        CompletionEntity completion = new CompletionEntity();
        completion.setHabit(habit);
        completion.setDateOfCompletion(new Date());

        return completionRepository.save(completion);
    }

    @Override
    public List<CompletionEntity> getCompletionsForHabit(Long habitId) {
        return completionRepository.findByHabitId(habitId);
    }
}
