package com.jphilips.task.util;

import com.jphilips.task.dto.TaskRequestDto;
import com.jphilips.task.dto.TaskResponseDto;
import com.jphilips.task.entity.Task;

import java.time.LocalDateTime;

public class TaskTestDataFactory {

    public static Task createTask(Long id, String userId, String task){
        return Task.builder()
                .id(id)
                .userId(userId)
                .task(task)
                .dateAdded(LocalDateTime.now())
                .isDone(false)
                .dateCompleted(null)
                .build();
    }
    public static TaskRequestDto createTaskRequestDto(String userId, String task) {
        return TaskRequestDto.builder()
                .userId(userId)
                .task(task)
                .dateAdded(LocalDateTime.now())
                .isDone(false)
                .dateCompleted(null)
                .build();
    }

    public static TaskResponseDto createTaskResponseDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getUserId(),
                task.getTask(),
                task.getDateAdded(),
                task.getIsDone(),
                task.getDateCompleted()
        );
    }

}
