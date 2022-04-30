package net.schoolvery.schoolveryserver.domain.user.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {

    private String name;
    private String nickname;
    private int phoneNum;
    private int schoolNum;
    private String school;
    private String email;
    private String password;
}
