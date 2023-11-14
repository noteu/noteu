package com.noteu.noteu.subject.service.impl;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.repository.MemberRepository;
import com.noteu.noteu.subject.dto.SubjectMemberResponseDto;
import com.noteu.noteu.subject.entity.Subject;
import com.noteu.noteu.subject.entity.SubjectMember;
import com.noteu.noteu.subject.repository.SubjectMemberRepository;
import com.noteu.noteu.subject.repository.SubjectRepository;
import com.noteu.noteu.subject.service.SubjectMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SubjectMemberServiceImpl implements SubjectMemberService {

    private final SubjectMemberRepository subjectMemberRepository;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;

    @Override
    public void save(String subjectCode, Long memberId) {
        Optional<Subject> optionalSubject = subjectRepository.findBySubjectCode(subjectCode);

        Subject subject = null;
        if(optionalSubject.isPresent()){
            subject = optionalSubject.get();
        }
        Member member = memberRepository.findById(memberId).orElse(null);

        subjectMemberRepository.save(SubjectMember.builder().subject(subject).member(member).build());
    }
}
