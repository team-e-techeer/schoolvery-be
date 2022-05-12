package net.schoolvery.schoolveryserver.domain.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String nickname;
    private String phoneNum;

}
