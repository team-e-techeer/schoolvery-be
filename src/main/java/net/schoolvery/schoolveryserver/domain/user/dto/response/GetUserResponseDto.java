package net.schoolvery.schoolveryserver.domain.user.dto.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class GetUserResponseDto {

    private UUID id;

    private String name;
    private String nickname;
    private String phoneNum;
    private String password;
    private UUID schoolId;
    private String email;
    private LocalDate modDate;
    private LocalDate regDate;
}
