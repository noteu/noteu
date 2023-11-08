package com.noteu.noteu.member.repository;

import com.noteu.noteu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}