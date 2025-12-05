package com.example.habit_tracker.controller;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.service.HabitService;
import com.example.habit_tracker.service.dto.HabitCreateDto;
import com.example.habit_tracker.service.dto.HabitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public List<HabitDto> getHabits(@PathVariable Long userId) {
        return habitService.getHabitsByUserId(userId);
    }

    @GetMapping("/{habitId}")
    public HabitDto getHabit(@PathVariable Long habitId) {
        return habitService.getHabitById(habitId);
    }

    @PostMapping
    public HabitDto createHabit(@RequestBody HabitCreateDto habit, @PathVariable Long userId) {
        return habitService.createHabit(habit, userId);
    }

    @DeleteMapping("/{habitId}")
    public void deleteHabit(@PathVariable Long habitId) {
        habitService.deleteById(habitId);
    }
}
