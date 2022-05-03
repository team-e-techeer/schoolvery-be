package net.schoolvery.schoolveryserver.domain.chat.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomCreateRequestDto {
    @NotNull
    private String name;
    @NotNull
    private UUID post_id;
}
