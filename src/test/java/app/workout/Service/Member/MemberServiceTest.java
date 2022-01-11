package app.workout.Service.Member;

import app.workout.Entity.Member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void findMemberTest(){
        //given
        Long memberId = memberService.join("testEmail", "testPassword", "tester", "tester");
        //when
        MemberDataDto member = memberService.findMemberOneBodyData(memberId);
        //then
        Assertions.assertEquals(memberId, member.getMemberId());
        Assertions.assertEquals(member.getEmail(),"testEmail");
        Assertions.assertEquals(member.getName(),"tester");

    }
}