package com.noteu.noteu.chat.service;

import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;

import java.util.List;

public interface RestChatService {

    //채팅방 하나 불러오기
    List<ChatRoomResponseDto> findAllById(Long friendId, Long loginId);

    //채팅방 생성
    List<ChatMessageResponseDto> findPastChat(Long roomId);

    ChatRoomResponseDto createRoom(Long subjectId, Long friendId, Long loginMemberId);
    //이전 채팅 보임
}
