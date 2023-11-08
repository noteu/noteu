package com.noteu.noteu.member.controller;

import com.noteu.noteu.member.dto.SignUpDto;
import com.noteu.noteu.member.dto.MemberDto;
import com.noteu.noteu.member.entity.Role;
import com.noteu.noteu.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signup(SignUpDto signUpDto) {
        return "fragments/member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignUpDto signUpDto, BindingResult bindingResult) {

        // 오류 메세지
        if (bindingResult.hasErrors()) {
            return "fragments/member/signup";
        }

        // 비밀번호 확인
        if (!signUpDto.getPassword1().equals(signUpDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "fragments/member/signup";
        }

        memberService.create(new MemberDto(null, signUpDto.getUsername(), signUpDto.getPassword1(), null, signUpDto.getMemberName(), signUpDto.getEmail(), signUpDto.getTel(), Role.equals(signUpDto.getRole()), null, null));
        return "redirect:/";
    }
}
