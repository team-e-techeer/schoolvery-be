package net.schoolvery.schoolveryserver.domain.board.dto.response;

import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {
    //private UUID id;
    private Integer id;
    private String name;
    private String description;
}