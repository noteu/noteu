package com.noteu.noteu.chat.service.impl;

import com.noteu.noteu.chat.repository.ChatParticipantRepository;
import com.noteu.noteu.chat.service.ChatParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChatParticipantServiceImpl implements ChatParticipantService {

    private final ChatParticipantRepository chatParticipantRepository;
}
