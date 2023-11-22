package com.noteu.noteu.chat.dto.response;

import com.noteu.noteu.member.dto.response.MemberResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponseDto {
    Long id;
    Long subjectId;
    String lastMessage;
    LocalDateTime lastMessageDateTime;
    List<MemberResponseDto> participants = new ArrayList<>();
}
