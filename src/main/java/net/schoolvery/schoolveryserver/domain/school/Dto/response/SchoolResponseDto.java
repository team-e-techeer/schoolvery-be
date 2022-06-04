package net.schoolvery.schoolveryserver.domain.school.Dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolResponseDto {

    private UUID id;
    private String schoolName;

}
