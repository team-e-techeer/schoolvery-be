package net.schoolvery.schoolveryserver.domain.hello.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HelloCreateRequestDto {
    private String name;
    private String greeting;
}

