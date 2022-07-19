package net.schoolvery.schoolveryserver.global.config.security;

import net.schoolvery.schoolveryserver.global.utils.jwt.JwtSecurityConfig;
import net.schoolvery.schoolveryserver.global.utils.jwt.TokenProvider;
import net.schoolvery.schoolveryserver.global.utils.jwt.error.JwtAccessDeniedHandler;
import net.schoolvery.schoolveryserver.global.utils.jwt.error.JwtAuthenticationEntryPoint;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메소드 단위로 추가하기 위한 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler, CorsFilter corsFilter) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.corsFilter = corsFilter;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/api/v1/users/**", "/api/v1/school/**", "/api/v1/categories/**"); // 관한 요청은 인증없이 접근 허용

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 토큰 사용하기에 disable

                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // Exception문들 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler).and()

                .headers()
                .frameOptions()
                .sameOrigin().and()

                // 세션사용을 따로하지 않기에, STATELESS 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests() // HttpServletRequest를 사용하는 요청들에대한 접근 제한 설정
                .anyRequest().authenticated() // 나머지 요청들은 전부 인증을 받아야한다.

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

    }

}
