package com.noteu.noteu.question.controller;

import com.noteu.noteu.common.CommonUtil;
import com.noteu.noteu.member.dto.MemberInfo;
import com.noteu.noteu.question.dto.request.AddRequestQuestionPostDTO;
import com.noteu.noteu.question.dto.response.DetailResponseQuestionPostDTO;
import com.noteu.noteu.question.dto.response.GetAllResponseQuestionPostDTO;
import com.noteu.noteu.question.service.QuestionPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/subjects/{subject-id}/questions")
public class QuestionPostController {

    private final QuestionPostService questionPostService;
    private final CommonUtil commonUtil;

    @GetMapping("add-form")
    public String addForm(@AuthenticationPrincipal MemberInfo memberInfo, @PathVariable("subject-id") Long subjectId, Map map) {

        map.put("subjectId", subjectId);

        return "layout/question/add";
    }

    @PostMapping
    public String addQuestionPost(@AuthenticationPrincipal MemberInfo memberInfo, @PathVariable("subject-id") Long subjectId,
                                  @ModelAttribute AddRequestQuestionPostDTO addRequestQuestionPostDTO) {

        String renderedContent = commonUtil.markdown(addRequestQuestionPostDTO.getQuestionPostContent());
        addRequestQuestionPostDTO.setQuestionPostContent(renderedContent);
        questionPostService.save(addRequestQuestionPostDTO, subjectId, memberInfo.getId());

        return "redirect:/subjects/{subject-id}/questions";
    }

    @GetMapping
    public String questionPostList(@AuthenticationPrincipal MemberInfo memberInfo, @PathVariable("subject-id") Long subjectId, Map map) {
        List<GetAllResponseQuestionPostDTO> dtoList = questionPostService.getAll();
        map.put("questionPost", dtoList);
        map.put("subjectId", subjectId);

        return "layout/question/list";
    }

    @GetMapping("/{questionPostId}")
    public String getQuestionPostById(@AuthenticationPrincipal MemberInfo memberInfo, @PathVariable("subject-id") Long subjectId,
            @PathVariable Long questionPostId, Map map) {
        try{
            DetailResponseQuestionPostDTO detailResponseQuestionPostDTO = questionPostService.getById(questionPostId);
            String renderedContent = commonUtil.markdown(detailResponseQuestionPostDTO.getQuestionPostContent());
            detailResponseQuestionPostDTO.setQuestionPostContent(renderedContent);
            map.put("questionPost", detailResponseQuestionPostDTO);
        } catch (Exception e) {
            System.out.println(e);
        }
        map.put("subjectId", subjectId);

        return "layout/question/detail";
    }
}
