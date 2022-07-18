package net.schoolvery.schoolveryserver.domain.board.dto.response;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {

    //private UUID id;
    private UUID id;
    private String name;
    private String description;
}
