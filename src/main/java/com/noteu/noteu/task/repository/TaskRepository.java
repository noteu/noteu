package com.noteu.noteu.task.repository;

import com.noteu.noteu.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}