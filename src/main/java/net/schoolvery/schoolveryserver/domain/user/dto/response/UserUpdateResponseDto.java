package net.schoolvery.schoolveryserver.domain.user.dto.response;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateResponseDto {

    private UUID id;
    private String nickname;
    private String phoneNum;
    private Integer schoolNum;
    private String school;
    private String email;
    private String password;

}
