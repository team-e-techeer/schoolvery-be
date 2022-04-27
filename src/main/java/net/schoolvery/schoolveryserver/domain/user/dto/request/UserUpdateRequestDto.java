package net.schoolvery.schoolveryserver.domain.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequestDto {

    private String nickname;
    private int phone_num;
//    private String school_id;
    private int school_num;
    private String school;
    private String email;
    private String password;

}
