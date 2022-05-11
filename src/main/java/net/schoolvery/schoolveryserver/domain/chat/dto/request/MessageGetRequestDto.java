package net.schoolvery.schoolveryserver.domain.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageGetRequestDto {
    private UUID room_id;
    private UUID member_id;
}
