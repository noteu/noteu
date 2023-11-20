package com.noteu.noteu.chat.service.impl;

import com.noteu.noteu.chat.converter.ChatConverter;
import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.entity.ChatParticipant;
import com.noteu.noteu.chat.entity.ChatRoom;
import com.noteu.noteu.chat.repository.ChatMessageRepository;
import com.noteu.noteu.chat.repository.ChatParticipantRepository;
import com.noteu.noteu.chat.repository.ChatRoomRepository;
import com.noteu.noteu.chat.service.RestChatService;
import com.noteu.noteu.member.entity.Member;
import com.noteu.noteu.member.repository.MemberRepository;
import com.noteu.noteu.subject.entity.Subject;
import com.noteu.noteu.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RestChatServiceImpl implements RestChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;
    private final ChatConverter converter;

    @Transactional(readOnly = true)
    @Override
    public List<ChatRoomResponseDto> findAllById(Long subjectId, Long loginId) {
        return chatRoomRepository.findAllBySubjectId(subjectId, loginId).stream()
                .map(chatRoom -> converter.chatRoomEntityToChatRoomDto(chatRoom, loginId))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatMessageResponseDto> findPastChat(Long roomId) {
        return chatMessageRepository.getAllByRoomId(roomId).stream()
                .map(converter::chatMessageEntityToChatMessageResponsedto)
                .toList();
    }

    @Override
    public ChatRoomResponseDto createRoom(Long subjectId, Long friendId, Long loginId) {
        Subject subject = subjectRepository.getReferenceById(subjectId);
        ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom(subject));
        enterRoom(chatRoom, friendId, loginId);

        return converter.chatRoomEntityToChatRoomDto(chatRoom, loginId);
    }

    // 생성된 방에 멤버 넣기
    private void enterRoom(ChatRoom chatRoom, Long friendId, Long loginId) {
        Member friend = memberRepository.getReferenceById(friendId);
        Member loginMember = memberRepository.getReferenceById(loginId);
        ChatParticipant save1 = chatParticipantRepository.save(new ChatParticipant(chatRoom, friend));
        ChatParticipant save2 = chatParticipantRepository.save(new ChatParticipant(chatRoom, loginMember));

        chatRoom.getParticipants().add(save1);
        chatRoom.getParticipants().add(save2);
        log.info("채팅방에 유저 담기 성공");
    }
}
