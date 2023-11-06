package com.noteu.noteu.chat.repository;

import com.noteu.noteu.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}