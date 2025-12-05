package com.example.habit_tracker.service.mapper;

import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.service.dto.HabitCreateDto;
import com.example.habit_tracker.service.dto.HabitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HabitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "completions", ignore = true)
    HabitEntity createDtoToEntity(HabitCreateDto dto);

    @Mapping(target = "userId", source = "entity.user.id")
    HabitDto entityToDto(HabitEntity entity);
}
