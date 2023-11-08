package com.noteu.noteu.member.service;

import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.dto.MemberDto;
import com.noteu.noteu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto create(MemberDto memberDto) {

        Member member = memberRepository.save(Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .profile(memberDto.getProfile())
                .memberName(memberDto.getMemberName())
                .email(memberDto.getEmail())
                .tel(memberDto.getTel())
                .role(memberDto.getRole())
                .build());

        return new MemberDto(member.getId(), member.getUsername(), member.getPassword(), member.getProfile(), member.getMemberName(), member.getEmail(), member.getTel(), member.getRole(), member.getCreatedAt(), member.getModifiedAt());
    }



}
