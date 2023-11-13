package com.noteu.noteu.chat.service.impl;

import com.noteu.noteu.chat.converter.ChatConverter;
import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.entity.ChatRoom;
import com.noteu.noteu.chat.repository.ChatMessageRepository;
import com.noteu.noteu.chat.repository.ChatParticipantRepository;
import com.noteu.noteu.chat.repository.ChatRoomRepository;
import com.noteu.noteu.chat.service.RestChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestChatServiceImpl implements RestChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatConverter converter;

    //채팅방 불러오기
    public List<ChatRoomResponseDto> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        return chatRoomRepository.findAll().stream()
                .map(converter::chatRoomEntityToChatRoomDto)
                .toList();
    }

    //채팅방 하나 불러오기
    public ChatRoomResponseDto findById(Long roomId) {
        //todo : (고도화) 채팅방 참여자에 포함되어있는지 확인해야한다.
        return chatRoomRepository.findById(roomId)
                .map(converter::chatRoomEntityToChatRoomDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ChatMessageResponseDto> pastChat(Long roomId) {
        return chatMessageRepository.getAllByRoomId(roomId).stream()
                .map(converter::chatMessageEntityToChatMessageResponsedto)
                .toList();
    }

    //채팅방 생성
    public ChatRoomResponseDto createRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        ChatRoom save = chatRoomRepository.save(chatRoom);

        return converter.chatRoomEntityToChatRoomDto(save);
    }
}
