package app.workout.Service.Member;

import app.workout.Entity.Member.BodyData;
import app.workout.Entity.Member.Member;
import app.workout.Messages.ErrorMessages;
import app.workout.Repository.Member.MemberRepository;
import app.workout.Service.FileUpload.FileUpload;
import app.workout.Service.Jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BodyDataService bodyDataService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final FileUpload fileUpload;

    /**
     * 로그인 로직
     *
     * @return*/
    public String login(String email, String password){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다"));
        if(member == null) return null;
        if(!passwordEncoder.matches(password, member.getPassword())) return null;
        return jwtTokenService.createToken(member.getId());
    }

    /**
     * 회원가입 로직
     * */
    @Transactional
    public Long join(String email, String password, String name, String nickname){
        emailCheck(email);
        passwordRegex(password);
        String encodePassword = passwordEncoder.encode(password);
        Member member = new Member(email, encodePassword, name, nickname);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 유저 아바타 변경 & 추가
     * */
    @Transactional
    public void changeAvatar(MultipartFile file, Long memberId){
        String avatarUrl = fileUpload.saveAvatar(file);
        Member member = findOne(memberId);
        member.changeAvatarUrl(avatarUrl);
    }

    /**
     * 단일 유저 찾기
     * */
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        });
    }

    /**
     * 전체 유저 찾기
     */
    public List<Member> findAll(Pageable pageable){
        return memberRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    /**
     * 유저의 이름과 닉네임 변경
     * */
    @Transactional
    public Long updateMember(Long memberId, @NotBlank String name, @NotBlank String nickname){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        });
        member.changeMember(name,nickname);
        return member.getId();
    }
    @Transactional
    public Long updateMember(Long memberId, Long loginId, @NotBlank String name, @NotBlank String nickname){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        });
        if(!Objects.equals(loginId, memberId)) throw new IllegalStateException("수정 권한이 없습니다");
        member.changeMember(name,nickname);
        return member.getId();
    }
    public MemberDataDto findMemberOneBodyData(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        });

        BodyData bodyDataByMember = bodyDataService.findBodyDataByMember(member.getId());

        return new MemberDataDto(member.getId(),member.getEmail(),member.getName(),member.getNickname(),
                bodyDataByMember.getId(),bodyDataByMember.getAge(),bodyDataByMember.getHeight(),bodyDataByMember.getWeight());

    }

    public void loginCheck(Long memberId) {
        if (memberId == null) throw new IllegalStateException("로그인 정보가 존재하지 않습니다");
    }

    private void passwordRegex(String password) {
        // password Regex 암호의 정규표현식
        String pattern = "^[a-zA-Z0-9\\d~!@#$%^&*]{10,}$";
        boolean matches = Pattern.matches(pattern, password);
        if(!matches) throw new IllegalStateException(ErrorMessages.ERROR_REGEX_PASSWORD);
    }

    private void emailCheck(String email){
        // 이메일 여부 체크
        memberRepository.findByEmail(email).ifPresent(m->{
            throw new IllegalStateException(ErrorMessages.EXISTS_EMAIL);
        });
    }

}
