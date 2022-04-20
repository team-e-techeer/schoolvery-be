package net.schoolvery.schoolveryserver.domain.user.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Data
public class GetUserResponseDto {

    private String id;

    private String name;
    private String nickname;
    private int phone_num;
    private int school_id;
    private int school_num;
    private String school;
    private String email;
    private String password;
    private LocalDate modDate;
    private LocalDate regDate;
}
