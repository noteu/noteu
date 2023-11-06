package com.noteu.noteu.reference.entity;

import com.noteu.noteu.audit.AuditingFields;
import com.noteu.noteu.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reference extends AuditingFields {

    @ManyToOne(fetch = FetchType.LAZY)
    private ReferenceRoom referenceRoom;

    @Column(nullable = false, length = 100)
    private String referenceName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
