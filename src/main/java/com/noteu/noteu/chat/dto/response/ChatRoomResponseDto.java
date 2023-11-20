package com.noteu.noteu.chat.dto.response;

import com.noteu.noteu.member.dto.response.MemberResponseDto;
import lombok.*;

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
    List<MemberResponseDto> participants = new ArrayList<>();
}
