package net.schoolvery.schoolveryserver.domain.user.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {

    private String name;
    private String nickname;
    private String phoneNum;
    private MultipartFile profileImageUrl;
    private UUID schoolId;
    private String email;
    private String password;
    private Set<AuthorityRequestDto> userAuthority;
    private String imgUrlString;

}
