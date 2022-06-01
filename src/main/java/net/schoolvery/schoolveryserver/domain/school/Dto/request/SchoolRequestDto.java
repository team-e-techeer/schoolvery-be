package net.schoolvery.schoolveryserver.domain.school.Dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolRequestDto {

    private UUID schoolId;
    private String schoolName;
}
