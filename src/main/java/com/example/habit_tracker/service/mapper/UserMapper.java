package com.example.habit_tracker.service.mapper;

import com.example.habit_tracker.entity.UserEntity;
import com.example.habit_tracker.service.dto.UserCreateDto;
import com.example.habit_tracker.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hash", source = "password")
    @Mapping(target = "habits", ignore = true)
    UserEntity createDtoToEntity(UserCreateDto dto);

    UserDto entityToDto(UserEntity entity);
}
