package com.example.habit_tracker.service.mapper;

import com.example.habit_tracker.entity.CompletionEntity;
import com.example.habit_tracker.service.dto.CompletionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompletionMapper {

    @Mapping(target = "habitId", source = "habit.id")
    CompletionDto entityToDto(CompletionEntity entity);
}
