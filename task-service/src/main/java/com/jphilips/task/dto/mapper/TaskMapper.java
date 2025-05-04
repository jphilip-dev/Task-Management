package com.jphilips.task.dto.mapper;

import com.jphilips.task.dto.TaskRequestDto;
import com.jphilips.task.dto.TaskResponseDto;
import com.jphilips.task.entity.Task;

public class TaskMapper {

    public static Task toEntity(TaskRequestDto taskRequestDto){
        return Task.builder()
                .userId(taskRequestDto.getUserId())
                .task(taskRequestDto.getTask())
                .dateAdded(taskRequestDto.getDateAdded())
                .isDone(taskRequestDto.getIsDone())
                .dateCompleted(taskRequestDto.getDateCompleted())
                .build();
    }

    public static TaskResponseDto toDto(Task task){
        return TaskResponseDto.builder()
                .id(task.getId())
                .userId(task.getUserId())
                .dateAdded(task.getDateAdded())
                .isDone(task.getIsDone())
                .dateCompleted(task.getDateCompleted())
                .build();
    }
}
