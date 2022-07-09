package net.schoolvery.schoolveryserver.domain.chat.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponseDto {
    private UUID room_id;
    private UUID member_id;
    private String message;
    private String regDate;

}
