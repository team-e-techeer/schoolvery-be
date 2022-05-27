package net.schoolvery.schoolveryserver.domain.school.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SchoolRequestDto {

    private UUID id;
    private String schoolName;
}
