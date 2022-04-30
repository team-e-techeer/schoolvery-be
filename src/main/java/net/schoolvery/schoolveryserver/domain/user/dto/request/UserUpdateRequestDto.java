package net.schoolvery.schoolveryserver.domain.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateRequestDto {

    private String nickname;
    private int phoneNum;
//    private String school_id;
    private int schoolNum;
    private String school;
    private String email;
    private String password;

}
