package app.workout.Service.Member;

import app.workout.Entity.Member.Member;
import app.workout.Repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
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
    public Long join(String email, String password, String name, String nickname){
        boolean isUsed = memberRepository.findByEmail(email).isPresent();
        if(isUsed) return null;
        Member member = new Member(email,password,name,nickname);
        memberRepository.save(member);
        return member.getId();
    }

}
