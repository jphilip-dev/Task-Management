package com.jphilips.task.repository;

import com.jphilips.task.dto.TaskResponseDto;
import com.jphilips.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(String userId);

}
