package net.schoolvery.schoolveryserver.domain.user.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorityRequestDto {
    private String authorityName;
}
