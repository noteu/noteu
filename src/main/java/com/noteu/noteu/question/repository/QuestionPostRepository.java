package com.noteu.noteu.question.repository;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.question.entity.QuestionPost;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface QuestionPostRepository extends JpaRepository<QuestionPost, Long> {
    List<QuestionPost> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);
}
