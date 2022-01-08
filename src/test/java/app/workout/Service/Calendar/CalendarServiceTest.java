package app.workout.Service.Calendar;

import app.workout.Entity.Calendar.Calendar;
import app.workout.Entity.Member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CalendarServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    CalendarService calendarService;

    @Test
    void findSchedule(){
        //given
        Member member = new Member("testA","1111","testerA","testerA");
        em.persist(member);
        LocalDate start = LocalDate.parse("2022-01-01");
        LocalDate end = LocalDate.parse("2022-01-08");
        Long sId = calendarService.addSchedule(member, start, end, "testTitle", "testMemo", "#ffffff");
        em.flush();
        em.clear();
        //when
        Calendar schedule = calendarService.getSchedule(sId);
        //then
        Assertions.assertEquals(schedule.getColor(),"#ffffff");
        Assertions.assertEquals(schedule.getStartDate(), LocalDate.parse("2022-01-01"));
        Assertions.assertEquals(schedule.getEndDate(), LocalDate.parse("2022-01-08"));
    }
}