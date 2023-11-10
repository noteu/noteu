package com.noteu.noteu.chat.service;

import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.entity.ChatRoom;

import java.util.List;

public interface RestChatService {

    List<ChatRoomResponseDto> findAllRoom();

    //채팅방 하나 불러오기
    ChatRoomResponseDto findById(Long roomId);

    //채팅방 생성
    ChatRoomResponseDto createRoom(String name);
}
