package com.example.habit_tracker.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class HabitDto {

    private Long id;
    private String habitName;
    private String habitDescription;
    private Long userId;
}
