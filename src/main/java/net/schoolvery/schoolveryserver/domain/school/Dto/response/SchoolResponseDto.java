package net.schoolvery.schoolveryserver.domain.school.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SchoolResponseDto {

    private UUID id;
    private String schoolName;

}
