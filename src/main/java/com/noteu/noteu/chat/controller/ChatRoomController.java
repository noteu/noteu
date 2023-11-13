package com.noteu.noteu.chat.controller;

import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.service.RestChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final RestChatService restChatService;

    // 커스텀 채팅 뷰
    @GetMapping("/rooom")
    public String roooms() {
        return "layout/chat/chat";
    }

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "fragments/content/chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomResponseDto> room() {
        return restChatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomResponseDto createRoom(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        return restChatService.createRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable Long roomId) {
        model.addAttribute("roomId", roomId);
        return "fragments/content/chat/roomdetail";
    }

    @ResponseBody
    @GetMapping("/room/past/{roomId}")
    public List<ChatMessageResponseDto> roomDetail(@PathVariable Long roomId) {
        List<ChatMessageResponseDto> chatMessageResponseDtos = restChatService.pastChat(roomId);
        log.info("이전 채팅 불러오기 : {}",chatMessageResponseDtos);
        return chatMessageResponseDtos;
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomResponseDto roomInfo(@PathVariable Long roomId) {
        return restChatService.findById(roomId);
    }
}
