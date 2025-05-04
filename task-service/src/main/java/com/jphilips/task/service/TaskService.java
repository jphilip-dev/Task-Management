package com.jphilips.task.service;

import com.jphilips.task.dto.TaskRequestDto;
import com.jphilips.task.dto.TaskResponseDto;
import com.jphilips.task.dto.mapper.TaskMapper;
import com.jphilips.task.entity.Task;
import com.jphilips.task.exception.custom.TaskNotFoundException;
import com.jphilips.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponseDto> getAllTask(){
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task newTask = TaskMapper.toEntity(taskRequestDto);

        taskRepository.save(newTask);

        return TaskMapper.toDto(newTask);
    }

    public TaskResponseDto getTaskById(Long id) {
        return TaskMapper.toDto(findTaskById(id));
    }

    public List<TaskResponseDto> getTaskByUserId(String userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(TaskMapper::toDto)
                .toList();
    }

    public TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto) {

        Task task = findTaskById(id);

        task.setTask(taskRequestDto.getTask());
        task.setIsDone(taskRequestDto.getIsDone());
        task.setDateCompleted(taskRequestDto.getDateCompleted());

        taskRepository.save(task);

        return TaskMapper.toDto(task);
    }

    public void deleteTaskById(Long id){
        taskRepository.delete(findTaskById(id));
    }


    /*
     * Helper methods
     */

    private Task findTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

}
