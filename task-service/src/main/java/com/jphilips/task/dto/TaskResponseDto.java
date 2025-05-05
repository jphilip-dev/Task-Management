package com.jphilips.task.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponseDto(Long id, String userId, String task,LocalDateTime dateAdded, Boolean isDone, LocalDateTime dateCompleted) {
}
