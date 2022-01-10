package app.workout.Service.Jwt;

import app.workout.Service.CommonConst;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenServiceTest {

    @Autowired
    JwtTokenService jwtTokenService;


    @Test
    void Test(){
        String token = jwtTokenService.createToken(1L);

        Claims test = jwtTokenService.getTest(token);
        Long loginMember = test.get("loginMember", Long.class);
        System.out.println("loginMember = " + loginMember);
    }
}