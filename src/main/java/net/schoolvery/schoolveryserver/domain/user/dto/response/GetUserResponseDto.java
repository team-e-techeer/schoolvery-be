package net.schoolvery.schoolveryserver.domain.user.dto.response;

import java.util.UUID;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Data
public class GetUserResponseDto {

    private UUID id;

    private String name;
    private String nickname;
    private int phoneNum;
//    private String school_id;
    private int schoolNum;
    private String school;
    private String email;
    private String password;
    private LocalDate modDate;
    private LocalDate regDate;
}
