package com.noteu.noteu.subject.service;

import com.noteu.noteu.subject.entity.SubjectMember;

public interface SubjectMemberService {
    // 추가(과목 참여)
    public void save(String subjectCode, Long memberId);
}
