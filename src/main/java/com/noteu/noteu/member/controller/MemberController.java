package com.noteu.noteu.member.controller;

import com.noteu.noteu.member.dto.MemberDto;
import com.noteu.noteu.member.dto.MemberEditDto;
import com.noteu.noteu.member.dto.MemberInfo;
import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.entity.Role;
import com.noteu.noteu.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberDetailsService memberDetailsService;

    @GetMapping("/account/{id}")
    public String account(@PathVariable("id") Long memberId, @AuthenticationPrincipal MemberInfo memberInfo, Model model) {
        Member member = memberDetailsService.findById(memberId);

        List<Role> list = new ArrayList<>(member.getRole());
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .tel(member.getTel())
                .introduction(member.getIntroduction())
                .profile(member.getProfile())
                .role(list)
                .build();

        if (!memberId.equals(memberInfo.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }
        model.addAttribute("member", memberDto);

        return "fragments/member/account";
    }

    @PostMapping("/account/{id}")
    public String editInformation(MemberEditDto memberEditDto) {
        memberDetailsService.updateUser(memberEditDto);
        return "redirect:/members/account/{id}";
    }
}
