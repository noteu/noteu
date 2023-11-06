package com.noteu.noteu.question.repository;

import com.noteu.noteu.question.entity.QuestionPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionPostRepository extends JpaRepository<QuestionPost, Long> {
}