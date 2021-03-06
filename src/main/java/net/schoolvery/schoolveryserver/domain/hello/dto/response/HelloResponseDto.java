package net.schoolvery.schoolveryserver.domain.hello.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HelloResponseDto {
    private String name;
    private String greeting;
}

