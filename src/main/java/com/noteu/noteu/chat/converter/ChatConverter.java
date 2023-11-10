package com.noteu.noteu.chat.converter;

import com.noteu.noteu.chat.dto.request.ChatMessageRequestDto;
import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.entity.ChatMessage;
import com.noteu.noteu.chat.entity.ChatRoom;

public interface ChatConverter {

    default ChatRoomResponseDto chatRoomEntityToChatRoomDto(ChatRoom chatRoom) {
        return ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .build();
    }

    default ChatMessage chatMessageRequestDtoToChatMessageEntity(ChatMessageRequestDto chatMessageRequestDto) {
        return ChatMessage.builder()
                .roomId(chatMessageRequestDto.getRoomId())
                .senderId(chatMessageRequestDto.getSenderId())
                .senderName(chatMessageRequestDto.getSenderName())
                .message(chatMessageRequestDto.getMessage())
                .build();
    }

    default ChatMessageResponseDto chatMessageEntityToChatMessageResponsedto(ChatMessage chatMessage){
        return ChatMessageResponseDto.builder()
                .roomId(chatMessage.getRoomId())
                .senderId(chatMessage.getSenderId())
                .senderName(chatMessage.getSenderName())
                .message(chatMessage.getMessage())
                .createdAt(String.valueOf(chatMessage.getCreatedAt()))
                .build();
    }

}
