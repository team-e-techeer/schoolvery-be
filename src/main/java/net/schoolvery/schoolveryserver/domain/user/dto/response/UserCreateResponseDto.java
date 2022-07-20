package net.schoolvery.schoolveryserver.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.schoolvery.schoolveryserver.domain.user.dto.request.AuthorityRequestDto;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserCreateResponseDto {

    private UUID id ;

    private String name;
    private String nickname;
    private String profileImageUrl;
    private String phoneNum;
    private UUID schoolId;
    private String email;
    private String password;
    private Set<AuthorityRequestDto> userAuthority;
}
