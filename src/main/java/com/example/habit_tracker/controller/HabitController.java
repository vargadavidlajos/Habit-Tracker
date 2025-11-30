package com.example.habit_tracker.controller;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping
    public List<HabitEntity> getHabits(@PathVariable Long userId) {
        return habitService.getHabitsByUserId(userId);
    }

    @GetMapping("/{habitId}")
    public HabitEntity getHabit(@PathVariable Long habitId) {
        return habitService.getHabitById(habitId);
    }

    @PostMapping
    public HabitEntity createHabit(@RequestBody HabitEntity habit) {
        return habitService.createHabit(habit);
    }

    @DeleteMapping("/{habitId}")
    public void deleteHabit(@PathVariable Long habitId) {
        habitService.deleteById(habitId);
    }
}
