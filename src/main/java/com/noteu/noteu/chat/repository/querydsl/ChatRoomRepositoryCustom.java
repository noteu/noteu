package com.noteu.noteu.chat.repository.querydsl;

import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomRepositoryCustom {

    List<ChatRoom> findAllBySubjectId(Long subjectId, Long loginId);
}
