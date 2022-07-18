package net.schoolvery.schoolveryserver.global.utils.jwt.error;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 유효한 자격증명을 제공하지 않고 접근할때 401 Unauthorized Error 리턴
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
