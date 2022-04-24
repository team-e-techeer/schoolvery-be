package net.schoolvery.schoolveryserver.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateResponseDto {

    private String id;
    private String nickname;
    private int phone_num;
    private int school_id;
    private int school_num;
    private String school;
    private String email;
    private String password;

}
