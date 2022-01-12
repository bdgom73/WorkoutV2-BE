package app.workout.Repository;

import app.workout.Entity.Member.Member;
import app.workout.Repository.Member.MemberRepository;
import app.workout.Repository.Member.MemberRepositoryImpl;
import app.workout.Service.Member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberRepositoryImplTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    void searchTest(){

        List<Member> test = memberRepository.findSearchByName("test");
        for (Member member : test) {
            System.out.println("member = " + member.getName());
        }
    }
}