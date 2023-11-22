package com.noteu.noteu.chat.controller;

import com.noteu.noteu.chat.dto.response.ChatMessageResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomInfoResponseDto;
import com.noteu.noteu.chat.dto.response.ChatRoomResponseDto;
import com.noteu.noteu.chat.service.RestChatService;
import com.noteu.noteu.member.dto.MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("subjects/{subject-id}/chats")
public class ChatRoomController {

    private final RestChatService restChatService;

    @GetMapping
    public String room(@PathVariable("subject-id") Long subjectId,
                       @AuthenticationPrincipal MemberInfo memberInfo,
                       Model model) {
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("memberId", memberInfo.getId());
        model.addAttribute("memberName", memberInfo.getMemberName());

        return "layout/chat/chat";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public ChatRoomInfoResponseDto room(@PathVariable("subject-id") Long subjectId,
                                        @AuthenticationPrincipal MemberInfo memberInfo) {

        return restChatService.findAllById(subjectId, memberInfo.getId());
    }

    // 채팅방 생성 (친구창에서 채팅 모양을 누르면 채팅이 생성됨) 이미 방이 생성되어 있다면??? 처리해야함..
    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<ChatRoomResponseDto> createRoom(@RequestBody Map<String, Long> requestBody,
                                                          @AuthenticationPrincipal MemberInfo memberInfo,
                                                          @PathVariable("subject-id") Long subjectId) {
        Long friendId = requestBody.get("riendId");

        return ResponseEntity.created(URI.create("/subjects/" + subjectId + "/chats"))
                .body(restChatService.createRoom(subjectId, friendId, memberInfo.getId()));
    }

    // 채팅방 입장 화면
    @GetMapping("/rooms/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable Long roomId) {
        model.addAttribute("roomId", roomId);

        return "fragments/content/chat/roomdetail";
    }

    @ResponseBody
    @GetMapping("/rooms/api")
    public List<ChatMessageResponseDto> pastChat(@RequestParam Long roomId) {
        List<ChatMessageResponseDto> chatMessageResponseDtos = restChatService.findPastChat(roomId);
        log.info("이전 채팅 불러오기 : {}", chatMessageResponseDtos);

        return chatMessageResponseDtos;
    }
}
