package net.schoolvery.schoolveryserver.domain.hello.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloUpdateRequestDto {
    private String greeting;
}
