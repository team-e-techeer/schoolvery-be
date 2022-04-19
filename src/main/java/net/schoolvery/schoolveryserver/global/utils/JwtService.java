package net.schoolvery.schoolveryserver.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {

    // Jwt 생성
    public String createJWt(int id) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("id", id)
                .setIssuedAt(now) // 발급 시간
                .setExpiration(new Date(now.getTime() + Duration.ofDays(30).toMillis())) // 만료기간
                .signWith(SignatureAlgorithm.HS256, "secret") // secret key 넣어줘야함
                .compact();
    }

    // Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    public String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    // JWT User id값 추출
    public int getUserid() throws Exception {
        String accessToken = getJwt();
        if (accessToken == null || accessToken.length() == 0) {
            System.out.println("존재하지 않습니다."); // 해당 부분 예외 exception 갈아 넣어줘야한다.
        }

        Jws<Claims> claims;
        claims = Jwts.parser()
                .setSigningKey("secret") // Sign key 넣어줘야한다.
                .parseClaimsJws(accessToken);


        return claims.getBody().get("id", Integer.class);

    }
}
