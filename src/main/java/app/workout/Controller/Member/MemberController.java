package app.workout.Controller.Member;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Member.Member;
import app.workout.Service.ArgumentResolver.Login.Login;
import app.workout.Service.CustomPageRequest;
import app.workout.Service.Member.MemberDataDto;
import app.workout.Service.Member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     * */
    @PostMapping("/login")
    public ReturnTypeV1<String> loginMember(@RequestBody LoginRequest loginRequest){
        String token = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.info("login = [{}]", loginRequest.getEmail());
        return new ReturnTypeV1<>(token);
    }

    /**
     * 회원가입
     * */
    @PostMapping("/join")
    public ReturnTypeV1<Long> joinMember(@RequestBody JoinRequest joinRequest){
        Long memberId = memberService.join(joinRequest.email,joinRequest.password,joinRequest.name,joinRequest.nickname);
        log.info("join member = [{}]", memberId);
        return new ReturnTypeV1<>(memberId);
    }

    /**
     * 로그인 유저 정보
     * */
    @GetMapping("/member")
    public ReturnTypeV1<MemberDataDto> LoginMember(@Login Long memberId){
        memberService.loginCheck(memberId);
        MemberDataDto findMember = memberService.findMemberOneBodyData(memberId);
        return new ReturnTypeV1<>(findMember);
    }

    /**
     * 유저 리스트
     * */
    @GetMapping("/members")
    public ReturnTypeV1<List<MemberResponse>> members(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sortString,
            @RequestParam(name = "direction", defaultValue = "desc") String direction
    ){

        PageRequest pageRequest = CustomPageRequest.getPageRequest(page,size,sortString,direction);
        List<MemberResponse> result = memberService.findAll(pageRequest).stream().map(MemberResponse::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    /**
     * 유저 검색
     * */

    @GetMapping("/members/{memberId}")
    public ReturnTypeV1<MemberResponse> getMember(@PathVariable("memberId") Long memberId){
        Member member = memberService.findOne(memberId);
        return new ReturnTypeV1<>(new MemberResponse(member));
    }

    /**
     * 유저 수정
     * */
    @PutMapping("/members/{memberId}")
    public ReturnTypeV1<MemberDataDto> editMember(@PathVariable("memberId") Long memberId, @Login Long loginId, @RequestBody EditMemberRequest memberRequest){
        Long mId = memberService.updateMember(memberId, loginId, memberRequest.getName(), memberRequest.getNickname());
        MemberDataDto findMember = memberService.findMemberOneBodyData(mId);
        return new ReturnTypeV1<>(findMember);
    }

    /**
     * 로그인 유저 아바타 추가 및 수정
     * */
    @PostMapping("/members/avatar")
    public void addMemberAvatar(@Login Long memberId, @RequestParam("avatar")MultipartFile file){
        memberService.loginCheck(memberId);
        memberService.changeAvatar(file, memberId);
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

    @Data
    private static class EditMemberRequest{
        private String name;
        private String nickname;
    }

    @Data
    private static class MemberFullResponse{
        private String email;
        private String name;
        private String nickname;

        private int age;
        private double height;
        private double weight;

        public MemberFullResponse(Member member) {
            this.email = member.getEmail();
            this.name = member.getName();
            this.nickname = member.getNickname();
        }
    }

    @Data
    private static class MemberResponse{
        private String email;
        private String name;
        private String nickname;

        public MemberResponse(Member member) {
            this.email = member.getEmail();
            this.name = member.getName();
            this.nickname = member.getNickname();
        }
    }
}
