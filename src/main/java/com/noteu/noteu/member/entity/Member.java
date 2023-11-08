package com.noteu.noteu.member.entity;

import com.noteu.noteu.audit.AuditingFields;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AuditingFields {

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false, length = 70)
    private String password;

    @Column(length = 300)
    private String profile;

    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 30)
    private String tel;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>();
}
