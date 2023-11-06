package com.noteu.noteu.chat.controller;

import com.noteu.noteu.chat.service.ChatParticipantService;
import com.noteu.noteu.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatParticipantController {

    private final ChatParticipantService chatParticipantService;
}
