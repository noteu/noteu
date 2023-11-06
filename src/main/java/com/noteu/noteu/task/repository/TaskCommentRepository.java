package com.noteu.noteu.task.repository;

import com.noteu.noteu.task.entity.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}