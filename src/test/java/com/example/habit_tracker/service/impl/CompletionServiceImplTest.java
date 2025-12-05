package com.example.habit_tracker.service.impl;


import com.example.habit_tracker.entity.CompletionEntity;
import com.example.habit_tracker.entity.HabitEntity;
import com.example.habit_tracker.repository.CompletionRepository;
import com.example.habit_tracker.service.HabitService;
import com.example.habit_tracker.service.dto.CompletionDto;
import com.example.habit_tracker.service.mapper.CompletionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompletionServiceImplTest {

    @Mock
    private CompletionRepository completionRepository;

    @Mock
    private CompletionMapper completionMapper;

    @Mock
    private HabitService habitService;

    @InjectMocks
    private CompletionServiceImpl completionService;

    @Test
    void testMarkHabitCompleted() {
        Long habitId = 42L;

        HabitEntity habit = new HabitEntity();
        habit.setId(habitId);
        habit.setHabitName("Exercise");

        when(habitService.getHabitEntityById(habitId)).thenReturn(habit);

        CompletionEntity savedCompletion = new CompletionEntity();
        savedCompletion.setId(1L);
        savedCompletion.setHabit(habit);
        savedCompletion.setDateOfCompletion(new Date());

        CompletionDto dto = new CompletionDto();
        dto.setId(1L);
        dto.setDateOfCompletion(savedCompletion.getDateOfCompletion());
        dto.setHabitId(habitId);

        when(completionRepository.save(Mockito.<CompletionEntity>any())).thenReturn(savedCompletion);
        when(completionMapper.entityToDto(savedCompletion))
                .thenReturn(dto);

        CompletionDto result = completionService.markHabitCompleted(habitId);

        assertEquals(1L, result.getId());
        assertEquals(habitId, result.getHabitId());
        assertNotNull(result.getDateOfCompletion());

        verify(habitService).getHabitEntityById(habitId);
        verify(completionRepository).save(Mockito.<CompletionEntity>any());
        verify(completionMapper).entityToDto(savedCompletion);
    }

    @Test
    void testGetCompletionsForHabit() {
        Long habitId = 42L;

        CompletionEntity c1 = new CompletionEntity();
        c1.setId(1L);
        c1.setHabit(new HabitEntity());
        c1.setDateOfCompletion(new Date());

        CompletionEntity c2 = new CompletionEntity();
        c2.setId(2L);
        c2.setHabit(new HabitEntity());
        c2.setDateOfCompletion(new Date());

        CompletionDto dto1 = new CompletionDto();
        dto1.setId(1L);
        dto1.setDateOfCompletion(c1.getDateOfCompletion());
        dto1.setHabitId(habitId);

        CompletionDto dto2 = new CompletionDto();
        dto2.setId(2L);
        dto2.setDateOfCompletion(c2.getDateOfCompletion());
        dto2.setHabitId(habitId);

        when(completionRepository.findByHabitId(habitId)).thenReturn(List.of(c1, c2));
        when(completionMapper.entityToDto(c1))
                .thenReturn(dto1);
        when(completionMapper.entityToDto(c2))
                .thenReturn(dto2);

        List<CompletionDto> result = completionService.getCompletionsForHabit(habitId);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        verify(completionRepository).findByHabitId(habitId);
        verify(completionMapper).entityToDto(c1);
        verify(completionMapper).entityToDto(c2);
    }
}
