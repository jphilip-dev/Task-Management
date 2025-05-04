package com.jphilips.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String task;

    @NotBlank
    private LocalDateTime dateAdded;

    @NotNull
    private Boolean isDone;

    @NotBlank
    private LocalDateTime dateCompleted;
}
