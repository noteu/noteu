package com.noteu.noteu.member.controller;

import com.noteu.noteu.member.dto.MemberInfo;
import com.noteu.noteu.member.dto.SignUpDto;
import com.noteu.noteu.member.service.MemberDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final MemberDetailsService memberDetailsService;

    @GetMapping("/sign-up")
    public String signup(SignUpDto signUpDto) {
        return "fragments/member/sign_up";
    }

    @PostMapping("/sign-up")
    public String signup(@Valid SignUpDto signUpDto, BindingResult bindingResult) {

        // 오류 메세지
        if (bindingResult.hasErrors()) {
            return "fragments/member/sign_up";
        }

        // 비밀번호 확인
        if (!signUpDto.getPassword1().equals(signUpDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "fragments/member/sign_up";
        }

        memberDetailsService.createUser(signUpDto);
        return "redirect:/members/login";
    }

    @GetMapping("/login")
    public String login() {
        return "fragments/member/sign_in";
    }

}
