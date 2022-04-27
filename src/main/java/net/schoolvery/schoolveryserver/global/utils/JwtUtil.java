package net.schoolvery.schoolveryserver.global.utils;

//import io.jsonwebtoken.security.Keys;
//import lombok.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//
//@Component
//public class JwtUtil {
//
//    @Value("${access-token-expires}")
//    private long ACCESS_TOKEN_EXPIRES;
//
//    @Value("${refresh-token-expires}")
//    private long REFRESH_TOKEN_EXPIRES;
//
//    private final static String BEAER_TYPE = "Bearer";
//    private final static String AUTENTITIES_KEY = "auth";
//a
//    private final Key accessKey;
//    private final Key refreshKey;
//
//    public JwtUtil(@Value("${jwt.access.secret}") byte[] accessSecret,
//                   @Value("${jwt.refresh.secret}") byte[] refreshSecret){
//        // byte[] keyBytes = Decoders.BASE64.decode(accessSecret);
//        this.accessKey = Keys.hmacShaKeyFor(accessSecret);
//        // keyBytes = Decoders.BASE64.decode(refreshSecret);
//        refreshKey = Keys.hmacShaKeyFor(refreshSecret);
//    }}
