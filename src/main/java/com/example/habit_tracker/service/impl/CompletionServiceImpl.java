package com.example.habit_tracker.service.impl;

import com.example.habit_tracker.entity.CompletionEntity;
import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.repository.CompletionRepository;
import com.example.habit_tracker.service.CompletionService;
import com.example.habit_tracker.service.HabitService;
import com.example.habit_tracker.service.dto.CompletionDto;
import com.example.habit_tracker.service.mapper.CompletionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletionServiceImpl implements CompletionService {

    private final CompletionRepository completionRepository;
    private final HabitService habitService;
    private final CompletionMapper completionMapper;

    @Override
    public CompletionDto markHabitCompleted(Long habitId) {
        HabitEntity habit = habitService.getHabitEntityById(habitId);

        CompletionEntity completion = new CompletionEntity();
        completion.setHabit(habit);
        completion.setDateOfCompletion(new Date());

        CompletionEntity savedCompletion = completionRepository.save(completion);
        return completionMapper.entityToDto(savedCompletion);
    }

    @Override
    public List<CompletionDto> getCompletionsForHabit(Long habitId) {
        List<CompletionEntity> completions = completionRepository.findByHabitId(habitId);
        return completions.stream()
                .map(completionMapper::entityToDto)
                .toList();
    }
}
