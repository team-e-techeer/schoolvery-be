package net.schoolvery.schoolveryserver.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateResponseDto {

    private String id;

    private String name;
    private String nickname;
    private int phone_num;
    private int school_id;
    private int school_num;
    private String school;
    private String email;
    private String password;

}
