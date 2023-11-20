package com.noteu.noteu.member.service;

import com.noteu.noteu.member.dto.MemberDetails;
import com.noteu.noteu.member.dto.SignUpDto;
import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.entity.Role;
import com.noteu.noteu.member.exception.UserAlreadyExistAuthenticationException;
import com.noteu.noteu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsManager{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(SignUpDto user) {

        if (userExists(user.getUsername())) {
            throw new UserAlreadyExistAuthenticationException("username exist");
        }

        Member member = Member.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword1()))
                .memberName(user.getMemberName())
                .email(user.getEmail())
                .tel(user.getTel())
                .role(Role.equals(user.getRole()))
                .build();

        memberRepository.save(member);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean userExists(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFound"));

        List<SimpleGrantedAuthority> list = member.getRole().stream()
                .map(Role::getValue)
                .map(SimpleGrantedAuthority::new)
                .toList();

        String profile = member.getProfile();
        if (profile == null) {
            profile = "/file/profile/default.png"; // 기본 이미지
        }

        return MemberDetails.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .profile(profile)
                .memberName(member.getMemberName())
                .authorities(list)
                .build();
    }
}
