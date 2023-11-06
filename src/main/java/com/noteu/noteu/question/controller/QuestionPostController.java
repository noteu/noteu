package com.noteu.noteu.question.controller;

import com.noteu.noteu.question.service.QuestionPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionPostController {

    private final QuestionPostService questionPostService;
}
