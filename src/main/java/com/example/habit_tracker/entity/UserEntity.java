package com.example.habit_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "pass_hash", nullable = false)
    private String hash;

    @OneToMany(mappedBy = "user")
    List<HabitEntity> habits;
}
