package com.noteu.noteu.subject.repository;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.subject.dto.SubjectResponseDto;
import com.noteu.noteu.subject.entity.Subject;
import com.noteu.noteu.subject.entity.SubjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SubjectMemberRepository extends JpaRepository<SubjectMember, Long> {
    @Query(value = "select sm.subject from SubjectMember as sm where sm.member=:member")
    ArrayList<Subject> findByMember(@Param("member") Member member);
}