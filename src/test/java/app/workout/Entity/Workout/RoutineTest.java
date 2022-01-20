package app.workout.Entity.Workout;

import app.workout.Entity.Member.Member;
import app.workout.Messages.ErrorMessages;
import app.workout.Service.FileUpload.FileUpload;
import app.workout.Service.Routine.RoutineService;
import app.workout.Service.Routine.VolumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Rollback(false)
class RoutineTest {

    @Autowired
    EntityManager em;

    @Autowired
    VolumeService volumeService;
    @Autowired
    RoutineService routineService;
    @Autowired
    FileUpload fileUpload;

    @Test
    void test(){
        String classpath = fileUpload.getClasspath();
        System.out.println("classpath = " + classpath);

        String noLoginUser = ErrorMessages.NO_LOGIN_USER;
        System.out.println("noLoginUser = " + noLoginUser);
    }

    @Test
    @Transactional
    void createRoutineTest(){
        //given
        Member member = em.find(Member.class, 1L);
        Workout workoutA = new Workout("workoutA",null,null);
        Workout workoutB = new Workout("workoutB",null,null);
        Workout workoutC = new Workout("workoutC",null,null);
        em.persist(workoutA);
        em.persist(workoutB);
        em.persist(workoutC);
        Volume volume = Volume.createVolume(20, 5, workoutA);
        Volume volume1 = Volume.createVolume(20, 5, workoutB);
        Volume[] v = new Volume[2];
        v[0] = volume;
        v[1] = volume1;
        //when
        Routine testA = Routine.createRoutine("testA", null, false, member , v);
        em.persist(testA);
        //then
    }
}