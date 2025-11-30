package com.example.habit_tracker.repository;

import com.example.habit_tracker.entity.CompletionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletionRepository extends JpaRepository<CompletionEntity, Long> {

    List<CompletionEntity> findByHabitId(Long habitId);
}
