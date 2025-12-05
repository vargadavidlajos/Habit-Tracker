package com.example.habit_tracker.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class CompletionDto {

    private Long id;
    private Date dateOfCompletion;
    private Long habitId;
}
