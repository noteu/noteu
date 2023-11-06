package com.noteu.noteu.question.service.impl;

import com.noteu.noteu.question.repository.QuestionCommentRepository;
import com.noteu.noteu.question.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QuestionCommentServiceImpl implements QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
}
