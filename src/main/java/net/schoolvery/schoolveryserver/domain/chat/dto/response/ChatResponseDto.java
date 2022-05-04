package net.schoolvery.schoolveryserver.domain.chat.dto.response;
import java.util.UUID;
import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatResponseDto {
    private UUID id;
    private String name;
    private Long post_id;
}
