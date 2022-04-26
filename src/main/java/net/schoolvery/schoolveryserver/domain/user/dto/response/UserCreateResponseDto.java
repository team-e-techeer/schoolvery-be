package net.schoolvery.schoolveryserver.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserCreateResponseDto {

    private String id ;

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
