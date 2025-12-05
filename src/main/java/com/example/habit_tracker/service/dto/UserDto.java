package com.example.habit_tracker.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDto {
    private Long id;
    private String username;
}
