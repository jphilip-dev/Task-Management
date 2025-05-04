package com.jphilips.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tm_tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String task;

    @Column(nullable = false)
    private LocalDateTime dateAdded;

    @Column(nullable = false, columnDefinition = "Boolean")
    private Boolean isDone;

    private LocalDateTime dateCompleted;
}
