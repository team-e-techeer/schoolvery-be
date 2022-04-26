package net.schoolvery.schoolveryserver.domain.board.dto.request;

import com.sun.istack.NotNull;
import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryUpdateRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
