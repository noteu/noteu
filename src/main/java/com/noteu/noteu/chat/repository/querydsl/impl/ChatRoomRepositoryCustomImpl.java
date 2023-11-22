package com.noteu.noteu.chat.repository.querydsl.impl;

import com.noteu.noteu.chat.entity.ChatRoom;
import com.noteu.noteu.chat.entity.QChatParticipant;
import com.noteu.noteu.chat.entity.QChatRoom;
import com.noteu.noteu.chat.repository.querydsl.ChatRoomRepositoryCustom;
import com.noteu.noteu.member.entity.QMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ChatRoomRepositoryCustomImpl extends QuerydslRepositorySupport implements ChatRoomRepositoryCustom {

    public ChatRoomRepositoryCustomImpl() {
        super(ChatRoom.class);
    }

    @Override
    public List<ChatRoom> findAllBySubjectId(Long subjectId, Long loginId) {
        QChatRoom chatRoom = QChatRoom.chatRoom;
        QChatParticipant chatParticipant = QChatParticipant.chatParticipant;
        QMember member = QMember.member;

        return from(chatRoom)
                .leftJoin(chatRoom.participants, chatParticipant)
                .leftJoin(chatParticipant.member, member)
                .where(chatRoom.subject.id.eq(subjectId)
                        .and(member.id.eq(loginId)))
                .select(chatRoom)
                .fetch();
    }

    @Override
    public boolean existsChatRoom(Long subjectId, Long friendId, Long loginId) {
        QChatRoom chatRoom = QChatRoom.chatRoom;
        QChatParticipant chatParticipant = QChatParticipant.chatParticipant;

        return from(chatRoom)
                .join(chatRoom.participants, chatParticipant)
                .where(chatRoom.subject.id.eq(subjectId)
                        .and(chatParticipant.member.id.in(Arrays.asList(friendId, loginId))))
                .fetchCount() > 0;
    }
}
