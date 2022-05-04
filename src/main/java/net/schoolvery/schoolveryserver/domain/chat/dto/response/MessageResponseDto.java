package net.schoolvery.schoolveryserver.domain.chat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponseDto {
    private UUID id;
    private UUID room_id;
    private UUID user_id;
    private String message;
}
