package com.noteu.noteu.subject.controller;

import com.noteu.noteu.member.dto.MemberInfo;
import com.noteu.noteu.subject.dto.SubjectMemberRequestDto;
import com.noteu.noteu.subject.dto.SubjectRequestDto;
import com.noteu.noteu.subject.dto.SubjectResponseDto;
import com.noteu.noteu.subject.entity.Subject;
import com.noteu.noteu.subject.service.SubjectMemberService;
import com.noteu.noteu.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMemberService subjectMemberService;

    // 과목 추가폼
    @GetMapping("/add-form")
    public String addForm(){
        return "layout/subject/add";
    }

    // 과목 추가
    @PostMapping
    public String addSubject(@AuthenticationPrincipal MemberInfo memberInfo, SubjectRequestDto subjectRequestDto){
        SubjectResponseDto subjectResponseDto = subjectService.save(subjectRequestDto);

        // Test Code
        log.info("과목 코드: {}", subjectResponseDto.getSubjectCode());
        log.info("memberInfo: {}", memberInfo.getId());
        subjectMemberService.save(subjectResponseDto.getSubjectCode(), memberInfo.getId());
        return "redirect:/subjects";
    }

    // 과목 코드 입력 페이지 폼
    @GetMapping("/input-code-form")
    public String inputCodeForm() {
        return "layout/subject/input_subject_code";
    }

    // 과목 코드 입력
    @PostMapping("/input-code")
    public String inputCode(@AuthenticationPrincipal MemberInfo memberInfo, SubjectMemberRequestDto subjectMemberRequestDto){
        // Test Code
        log.info("memberInfo: {}", memberInfo);
        subjectMemberService.save(subjectMemberRequestDto.getSubjectCode(), memberInfo.getId());
        return "redirect:/subjects";
    }

    // 과목 리스트
    @GetMapping
    public String list(@AuthenticationPrincipal MemberInfo memberInfo, Model m){
        log.info("memberInfo: {}", memberInfo);
        List<Subject> list = subjectService.getAll(memberInfo.getId());

        if (list != null)
            if (!list.isEmpty())
                m.addAttribute("list", list);
        return "layout/subject/list";

    }

//     과목 상세보기
    @GetMapping("/{subjectId}")
    public String detailSubject(@PathVariable Long subjectId, Model m){
        log.info("subjectId: {}", subjectId);
        SubjectResponseDto subjectResponseDto = subjectService.getSubject(subjectId);
        m.addAttribute("subject", subjectResponseDto);
        return "layout/subject/detail";
    }

//    // 과목 삭제
//    @PostMapping("")
//    public void delSubject(){
//
//    }
}
