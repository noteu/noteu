package com.noteu.noteu.subject.entity;

import com.noteu.noteu.audit.AuditingFields;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject extends AuditingFields {

    @Column(nullable = false, length = 30)
    private String subjectName;
}
