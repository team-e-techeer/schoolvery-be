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
public class RoomUpdateRequestDto {
    private String name;
    private Long post_id;
}