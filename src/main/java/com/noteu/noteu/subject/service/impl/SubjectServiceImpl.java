package com.noteu.noteu.subject.service.impl;

import com.noteu.noteu.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl {

    private final SubjectRepository subjectRepository;
}
