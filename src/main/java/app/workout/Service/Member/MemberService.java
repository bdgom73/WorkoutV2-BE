package app.workout.Service.Member;

import app.workout.Entity.Member.Member;
import app.workout.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 로직
     * */
    public Long login(String email, String password){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다"));
        if(member == null) return null;
        if(!member.getPassword().equals(password)) return null;
        return member.getId();
    }

    /**
     * 회원가입 로직
     * */
    @Transactional
    public Long join(String email, String password, String name, String nickname){
        boolean isUsed = memberRepository.findByEmail(email).isPresent();
        if(isUsed) return null;
        Member member = new Member(email,password,name,nickname);
        memberRepository.save(member);
        return member.getId();
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
    public void updateMember(Long memberId, @NotBlank String name, @NotBlank String nickname){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        });
        member.changeMember(name,nickname);
    }

}
