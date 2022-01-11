package app.workout.Service.Jwt;

import app.workout.Service.CommonConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static app.workout.Service.CommonConst.JWT_PROPERTIES;

@Component
@PropertySource(JWT_PROPERTIES)
public class JwtTokenService {

    @Value("${JWT_TOKEN_EXPIRED}")
    private Long tokenValidTime;

    @Value("${JWT_TOKEN_SECRET}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.encodeBase64String(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * JWT 토큰 생성
     * */
    public String createToken(Long memberId) {
        Date now = new Date();
        return Jwts.builder()
                .claim(CommonConst.LOGIN_MEMBER, memberId) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret 값 세팅
                .compact();
    }

    /**
     * 토큰에서 유저 정보 가져오기
     * @param token 순수 토큰
     * @return TokenLoginDTO
     * */
    public Long getUserInformation(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get(CommonConst.LOGIN_MEMBER,Long.class);
    }

    public Claims getTest(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
    }

    /**
     * authorization 헤더의 토큰에서 순수 Token 추출
     * @param authorizationToken Bearer Token 형식의 인증 토큰
     * @return String
     * */
    public String extractToken(String authorizationToken){
        TokenEmpty(authorizationToken);
        InvalidToken(authorizationToken);
        return authorizationToken.replaceAll("Bearer ", "");
    }

    /**
     * 토큰의 만료 여부 확인
     * @param token 순수 token
     * @return 만약 만료시간이 지나지 않았으면 <u>TRUE</u> 그렇지 않으면 <u>FALSE</u>
     * */
    private boolean isExpired(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    public void expired(String token) {
       if(!isExpired(token)){
           throw new IllegalStateException("만료된 토큰입니다");
       }
    }


    /**
     * authorization 헤더의 token 의 Empty 여부 확인
     * @param authorizationToken Bearer Token 형식의 인증 토큰
     * @throws NullPointerException 토큰이 존재하지 않음
     * */
    private void TokenEmpty(String authorizationToken){
        if(authorizationToken == null){
            throw new NullPointerException("Authorization is Empty");
        }
    }

    /**
     * authorization 헤더의 Bearer 존재 여부 확인
     * @param authorizationToken Bearer Token 형식의 인증 토큰
     * @throws IllegalStateException 유효하지 않은 토큰
     * */
    private void InvalidToken(String authorizationToken){
        if(!authorizationToken.contains("Bearer ")){
            throw new IllegalStateException("Invalid Token");
        }
    }



}
