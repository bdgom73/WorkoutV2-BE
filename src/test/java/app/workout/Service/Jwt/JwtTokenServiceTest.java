package app.workout.Service.Jwt;

import io.jsonwebtoken.Claims;
import net.bytebuddy.utility.RandomString;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StopWatch;

import java.nio.charset.StandardCharsets;
import java.util.Random;

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

    @Test
    void randomStringTest(){
        String make = RandomString.hashOf(39);
        System.out.println("make = " + make);
    }
}