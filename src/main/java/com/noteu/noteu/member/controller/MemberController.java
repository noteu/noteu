package com.noteu.noteu.member.controller;

import com.noteu.noteu.member.dto.MemberDto;
import com.noteu.noteu.member.dto.MemberEditDto;
import com.noteu.noteu.member.dto.MemberInfo;
import com.noteu.noteu.member.dto.MemberPasswordDto;
import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.entity.Role;
import com.noteu.noteu.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/account/{id}")
    public String account(@PathVariable("id") Long memberId, @AuthenticationPrincipal MemberInfo memberInfo, Model model) {

        if (!memberId.equals(memberInfo.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }

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

        model.addAttribute("member", memberDto);

        return "layout/member/account";
    }

    @PostMapping("/account/{id}")
    public String editInformation(MemberEditDto memberEditDto) {
        memberDetailsService.updateUser(memberEditDto);
        return "redirect:/members/account/{id}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long memberId, @AuthenticationPrincipal MemberInfo memberInfo) {
        memberDetailsService.deleteUser(memberId, memberInfo.getUsername());
        return "redirect:/auth/login";
    }

    @GetMapping("/password/{id}")
    public String passwordForm(@PathVariable("id") Long memberId, @AuthenticationPrincipal MemberInfo memberInfo, Model model) {

        if (!memberId.equals(memberInfo.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }
        model.addAttribute("member", memberInfo);
        return "layout/member/change_password";
    }

    @PostMapping("/password/{id}")
    public String changePassword(@RequestBody MemberPasswordDto memberPasswordDto) {
        memberDetailsService.changePassword(memberPasswordDto);
        return "redirect:/members/account/{id}";
    }

    @ResponseBody
    @PostMapping("/pw-check")
    public String passwordCheck(@AuthenticationPrincipal MemberInfo memberInfo, MemberPasswordDto memberPasswordDto){

        Member member = memberDetailsService.findById(memberInfo.getId());
        String previousPassword = memberPasswordDto.getPreviousPassword();

        if (passwordEncoder.matches(previousPassword, member.getPassword())) {
            return "1";
        } else {
            return "0";
        }
    }
}
