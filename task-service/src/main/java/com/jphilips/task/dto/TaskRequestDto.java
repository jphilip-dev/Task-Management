package com.jphilips.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
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
