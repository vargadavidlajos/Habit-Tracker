package com.example.habit_tracker.repository;

import com.example.habit_tracker.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

    List<HabitEntity> findByUserId(Long userId);
}
