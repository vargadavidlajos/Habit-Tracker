package com.example.habit_tracker.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class HabitCreateDto {

    @NotBlank
    @Size(max = 100)
    private String habitName;

    @Size(max = 500)
    private String habitDescription;
}
