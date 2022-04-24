package net.schoolvery.schoolveryserver.domain.user.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponseDto {

    private String email;
    private String password;

}
