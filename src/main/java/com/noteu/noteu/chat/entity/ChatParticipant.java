package com.noteu.noteu.chat.entity;

import com.noteu.noteu.audit.AuditingFields;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChatParticipant extends AuditingFields {

    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    private String roomName;
}
