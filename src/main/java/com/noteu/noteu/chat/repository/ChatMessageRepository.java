package com.noteu.noteu.chat.repository;

import com.noteu.noteu.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> getAllByRoomId(Long roomId);
}