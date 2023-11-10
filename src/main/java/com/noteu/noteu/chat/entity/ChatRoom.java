package com.noteu.noteu.chat.entity;

import com.noteu.noteu.audit.AuditingFields;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatRoom extends AuditingFields {

    private String roomName;

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private final Set<ChatParticipant> participants = new LinkedHashSet<>();

}
