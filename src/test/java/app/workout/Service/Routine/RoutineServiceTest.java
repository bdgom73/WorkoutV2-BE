package app.workout.Service.Routine;

import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Entity.Workout.Workout;
import app.workout.Repository.WorkoutRepository;
import app.workout.Service.FileUpload;
import app.workout.Service.Workout.WorkoutService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class RoutineServiceTest {

    @Autowired
    RoutineService routineService;
    @Autowired
    WorkoutRepository workoutRepository;
    @Autowired
    VolumeService volumeService;
    @Autowired
    EntityManager em;

    @Test
    void addRoutineAndFindRoutine(){
        //given
        Workout workoutA = new Workout("workoutA",null,null);
        Workout workoutB = new Workout("workoutB",null,null);
        Workout workoutC = new Workout("workoutC",null,null);

        workoutRepository.save(workoutA);
        workoutRepository.save(workoutB);
        workoutRepository.save(workoutC);

        Volume v1 = Volume.createVolume(20, 20, workoutA);
        Volume v2 = Volume.createVolume(20, 20, workoutB);
        Volume v3 = Volume.createVolume(20, 20, workoutC);

        Long rid = routineService.createRoutine(1L, "testA", null, Boolean.FALSE, v1, v2, v3);

        em.flush();
        em.clear();

        //when
        Routine one = routineService.findOne(rid);
        //then
        assertEquals(one.getId(), rid);
        assertEquals(one.getTitle(), "testA");
        assertEquals(one.getMember().getId(), 1L);
    }

    @Test
    void addVolume(){
        //given
        Workout workoutA = new Workout("workoutA",null,null);
        Workout workoutB = new Workout("workoutB",null,null);
        Workout workoutC = new Workout("workoutC",null,null);

        workoutRepository.save(workoutA);
        workoutRepository.save(workoutB);
        workoutRepository.save(workoutC);

        Volume v1 = Volume.createVolume(20, 20, workoutA);
        Volume v2 = Volume.createVolume(20, 20, workoutB);
        Volume v3 = Volume.createVolume(20, 20, workoutC);

        Long rid = routineService.createRoutine(1L, "testA", null, Boolean.FALSE, v1, v2, v3);

        em.flush();
        em.clear();

        //when
        Long idx = routineService.addVolume(rid, workoutB.getId(), 20, 30);

        Routine one = routineService.findOne(idx);
        //then
        assertEquals(one.getVolumes().size(), 4);
    }

    @Test
    void findAllTest(){
        //given
        Workout workoutA = new Workout("workoutA",null,null);
        Workout workoutB = new Workout("workoutB",null,null);
        Workout workoutC = new Workout("workoutC",null,null);

        workoutRepository.save(workoutA);
        workoutRepository.save(workoutB);
        workoutRepository.save(workoutC);

        Volume v1 = Volume.createVolume(20, 20, workoutA);
        Volume v2 = Volume.createVolume(20, 20, workoutB);
        Volume v3 = Volume.createVolume(20, 20, workoutC);

        Long rid = routineService.createRoutine(1L, "testA", null, Boolean.FALSE, v1, v2, v3);

        em.flush();
        em.clear();

        //when
        Routine one = routineService.findRoutine(rid);
        //then

    }


}