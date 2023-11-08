package com.noteu.noteu.member.dto;

import com.noteu.noteu.member.entity.Role;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;

    private String username;

    private String password;

    private String profile;

    private String memberName;

    private String email;

    private String tel;

    private List<Role> role = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
