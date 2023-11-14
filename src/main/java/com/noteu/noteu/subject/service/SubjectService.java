package com.noteu.noteu.subject.service;

import com.noteu.noteu.subject.dto.SubjectRequestDto;
import com.noteu.noteu.subject.dto.SubjectResponseDto;
import com.noteu.noteu.subject.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public interface SubjectService {
    public SubjectResponseDto save(SubjectRequestDto subjectRequestDto);
    public SubjectResponseDto getSubject(Long id);
    public List<Subject> getAll(Long memberId);
    public void delSubject(Long id);
}
