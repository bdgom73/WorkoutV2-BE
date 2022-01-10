package app.workout.Controller.Member;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Service.Member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ReturnTypeV1<String> loginMember(@RequestBody LoginRequest loginRequest){
        String token = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return new ReturnTypeV1<>(token);
    }

    @PostMapping("/join")
    public ReturnTypeV1<Long> joinMember(@RequestBody JoinRequest joinRequest){
        Long memberId = memberService.join(joinRequest.email,joinRequest.password,joinRequest.name,joinRequest.nickname);
        return new ReturnTypeV1<>(memberId);
    }

    @Data
    private static class LoginRequest{
        private String email;
        private String password;
    }
    @Data
    private static class JoinRequest{
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String name;
        @NotBlank
        private String nickname;
    }
}
