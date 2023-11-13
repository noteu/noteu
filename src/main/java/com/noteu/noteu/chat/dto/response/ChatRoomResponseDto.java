package com.noteu.noteu.chat.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomResponseDto {
    Long id;
    String roomName;
}
