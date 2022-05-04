package net.schoolvery.schoolveryserver.domain.user.dto.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class GetUserResponseDto {

    private UUID id;

    private String name;
    private String nickname;
    private String phoneNum;
    private Integer schoolNum;
    private String school;
    private String email;
    private LocalDate modDate;
    private LocalDate regDate;
}
