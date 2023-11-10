package com.noteu.noteu.chat.repository;

import com.noteu.noteu.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}