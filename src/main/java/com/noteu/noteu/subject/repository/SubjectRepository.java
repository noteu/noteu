package com.noteu.noteu.subject.repository;

import com.noteu.noteu.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}