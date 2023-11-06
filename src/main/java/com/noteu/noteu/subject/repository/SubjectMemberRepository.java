package com.noteu.noteu.subject.repository;

import com.noteu.noteu.subject.entity.SubjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectMemberRepository extends JpaRepository<SubjectMember, Long> {
}