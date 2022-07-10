package net.schoolvery.schoolveryserver.domain.user.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ConfirmEmailRequestDto {

    private String email;
}
