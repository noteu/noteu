package com.noteu.noteu.chat.service;

import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;

import java.util.List;

public interface RestChatService {

    List<ChatRoomResponseDto> findAllRoom();

    //채팅방 하나 불러오기
    ChatRoomResponseDto findById(Long roomId);

    //채팅방 생성
    List<ChatMessageResponseDto> pastChat(Long roomId);

    ChatRoomResponseDto createRoom(String name);
    //이전 채팅 보임
}
