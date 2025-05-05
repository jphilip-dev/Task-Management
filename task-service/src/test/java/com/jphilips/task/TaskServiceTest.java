package com.jphilips.task;

import com.jphilips.task.dto.TaskRequestDto;
import com.jphilips.task.dto.TaskResponseDto;
import com.jphilips.task.dto.mapper.TaskMapper;
import com.jphilips.task.entity.Task;
import com.jphilips.task.exception.custom.TaskNotFoundException;
import com.jphilips.task.repository.TaskRepository;
import com.jphilips.task.service.TaskService;
import com.jphilips.task.util.TaskTestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Mock the repository
    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService; // The service under test


    @Test
    public void shouldReturnAllTask() {
        // Given
        Task task1 = TaskTestDataFactory.createTask(1L,"user1", "Task 1");
        Task task2 = TaskTestDataFactory.createTask(2L,"user2", "Task 2");

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        TaskResponseDto taskResponseDto1 = TaskTestDataFactory.createTaskResponseDto(task1);
        TaskResponseDto taskResponseDto2 = TaskTestDataFactory.createTaskResponseDto(task2);

        when(taskMapper.toDto(task1)).thenReturn(taskResponseDto1);
        when(taskMapper.toDto(task2)).thenReturn(taskResponseDto2);

        // When
        List<TaskResponseDto> result = taskService.getAllTask();

        // Then
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).task());
        assertEquals("Task 2", result.get(1).task());
    }

    @Test
    public void shouldCreateTask(){

        TaskRequestDto taskRequestDto = TaskTestDataFactory.createTaskRequestDto("user1","task1");
        Task task = TaskTestDataFactory.createTask(1L,taskRequestDto.getUserId(),taskRequestDto.getTask());

        when(taskMapper.toEntity(taskRequestDto)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);

        TaskResponseDto taskResponseDto = TaskTestDataFactory.createTaskResponseDto(task);

        when(taskMapper.toDto(task)).thenReturn(taskResponseDto);

        TaskResponseDto result = taskService.createTask(taskRequestDto);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals("task1", result.task());
        assertEquals("user1", result.userId());
    }

    @Test
    public void shouldReturnTaskById(){
        Task task1 = TaskTestDataFactory.createTask(1L,"user1", "task1");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        TaskResponseDto taskResponseDto1 = TaskTestDataFactory.createTaskResponseDto(task1);

        when(taskMapper.toDto(task1)).thenReturn(taskResponseDto1);

        TaskResponseDto response = taskService.getTaskById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
    }

    @Test
    public void shouldThrowExceptionWhenGettingNonExistentId(){
        Long id = 1L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () ->taskService.getTaskById(id));
    }

    @Test
    public void shouldDeleteTask(){
        Long id = 1L;
        Task task = TaskTestDataFactory.createTask(id,"user1", "Task 1");

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        taskService.deleteTaskById(id);

        verify(taskRepository).delete(task);
    }
    @Test
    public void shouldThrowExceptionWhenDeletingNonExistentTask() {
        // Given
        Long id = 1L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTaskById(id));
        verify(taskRepository, never()).delete(any());
    }
}
