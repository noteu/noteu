package com.noteu.noteu.chat.entity;

import com.noteu.noteu.audit.AuditingFields;
import com.noteu.noteu.subject.entity.Subject;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatRoom extends AuditingFields {

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
}
