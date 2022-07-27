package net.schoolvery.schoolveryserver.domain.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomJoinRequestDto {
    private UUID user_id;
    private UUID room_id;

}
