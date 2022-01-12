package app.workout.Service.Member;

import app.workout.Entity.Member.BodyData;
import app.workout.Entity.Member.Member;
import app.workout.Repository.Member.BodyDataRepository;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BodyDataServiceTest {

    @Autowired
    BodyDataService bodyDataService;
    @Autowired
    BodyDataRepository bodyDataRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void createBodyDataTest(){
        //given
        Member member = new Member("test1","1111","test","tester");
        em.persist(member);

        //when
        // 추가
        Long dataId1 = bodyDataService.createBodyData(member.getId(),20, 180D, 90D);
        Long dataId2= bodyDataService.createBodyData(member.getId(),20, 180D, 88D);
        Long dataId3 = bodyDataService.createBodyData(member.getId(),20, 180D, 78D);
        // 찾기
        BodyData bodyData = bodyDataService.findBodyDataByMember(member.getId());

        //then
        Assertions.assertEquals(dataId1,6L);
        Assertions.assertEquals(dataId2,7L);
        Assertions.assertEquals(dataId3,8L);
        Assertions.assertEquals(bodyData.getWeight(),78D);
    }

    @Test
    public void getBodyDataListTest(){
        //given
        Member member = new Member("test1","1111","test","tester");
        em.persist(member);
        Long dataId1 = bodyDataService.createBodyData(member.getId(), 20, 180D, 90D);
        Long dataId2= bodyDataService.createBodyData(member.getId(), 20, 180D, 85D);
        Long dataId3 = bodyDataService.createBodyData(member.getId(), 20, 180D, 80D);
        //when
        List<BodyData> bds = bodyDataService.findAll(PageRequest.of(0, 10));
        //then
        Long t = 1L;
        for(BodyData data : bds){
            t++;
            Assertions.assertEquals(data.getId(),t);
        }
    }

    @Test
    public void updateTest(){
        //given
        Member member = new Member("test1","1111","test","tester");
        em.persist(member);
        Long dataId = bodyDataService.createBodyData(member.getId(),20, 180D, 78D);
        em.flush();
        em.clear();
        //when
        Long findId = bodyDataService.updateBodyData( dataId, 20, 180D, 60D );
        //then
        Assertions.assertEquals(findId,dataId);
    }

    @Test
    @Rollback(value = true)
    public void deleteTest(){
        //given
        Member member = new Member("test1","1111","test","tester");
        em.persist(member);
        Long dataId = bodyDataService.createBodyData(member.getId(),20, 180D, 78D);
        em.flush();
        em.clear();
        //when
        bodyDataService.deleteBodyData(dataId);
        //then
        Assertions.assertThrows(IllegalStateException.class, ()->{
            bodyDataService.findById(dataId);
        });
    }

}