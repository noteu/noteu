package com.noteu.noteu.chat.repository.querydsl;

import com.noteu.noteu.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomRepositoryCustom {

    List<ChatRoom> findAllBySubjectId(Long subjectId, Long loginId);

    boolean existsChatRoom(Long subjectId, Long friendId, Long loginId);
}
