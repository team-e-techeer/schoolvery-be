package net.schoolvery.schoolveryserver.domain.chat.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomFindResponseDto {
    private UUID id;
    private UUID roomId;
    private UUID memberId;
}
