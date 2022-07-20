package net.schoolvery.schoolveryserver.global.utils.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// JWT 커스텀 필터
// GenericFilterBean을 extends하여 doFilter Override 실제 필터링 로직은 doFilter 내부에 작성
@Slf4j
public class JwtFilter extends GenericFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String jwt = resolveToken(httpServletRequest); // resolveToken을 통해 토큰을 받아와 유효성 검증 후, 정상 토큰일시 SecurityContext에 저장
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Security Context에 '{}' 인증정보 저장, uri : {}",authentication.getName(), requestURI);
        } else
            log.info("유효한 JWT 토큰 존재하지않음, uri : {}", requestURI);

        chain.doFilter(request, response);
    }

    // Request Header에서 토큰정보를 꺼내오기 위한 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
