package com.example.habit_tracker.controller;

import com.example.habit_tracker.service.CompletionService;
import com.example.habit_tracker.service.dto.CompletionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/habits/{habitId}/completions")
@RequiredArgsConstructor
public class CompletionController {

    private final CompletionService completionService;

    @PostMapping
    public CompletionDto completeHabit(@PathVariable Long habitId) {
        return completionService.markHabitCompleted(habitId);
    }

    @GetMapping
    public List<CompletionDto> getCompletions(@PathVariable Long habitId) {
        return completionService.getCompletionsForHabit(habitId);
    }
}
