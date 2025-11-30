package com.example.habit_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CompletionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_of_completion")
    @Temporal(TemporalType.DATE)
    private Date dateOfCompletion;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private HabitEntity habit;
}
