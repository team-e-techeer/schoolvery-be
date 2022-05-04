package net.schoolvery.schoolveryserver.domain.chat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomCreateRequestDto {
    private String name;
    private Long post_id;
}
