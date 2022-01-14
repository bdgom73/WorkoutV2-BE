package app.workout.Repository;

import app.workout.Entity.Member.Member;
import app.workout.Repository.Member.MemberRepository;
import app.workout.Repository.Member.MemberRepositoryImpl;
import app.workout.Service.Member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class MemberRepositoryImplTest {
    
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    void searchTest(){

        PageRequest member_id = PageRequest.of(0, 100, Sort.by("name").by("email").ascending());
        List<Member> test = memberRepository.findSearchByName("test",member_id);
        for (Member member : test) {
            System.out.println("member = " + member.getId());
            System.out.println("member = " + member.getName());
        }
    }
}