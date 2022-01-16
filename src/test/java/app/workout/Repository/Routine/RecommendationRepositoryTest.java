package app.workout.Repository.Routine;

import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Service.Routine.RoutineService;
import app.workout.Service.Routine.VolumeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@SpringBootTest
@Rollback(false)
class RecommendationRepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    RecommendationRepository recommendationRepository;
    @Autowired
    RoutineRepository routineRepository;
    @Autowired
    RoutineService routineService;
    @Autowired
    VolumeService volumeService;

    @Test
    @Rollback
    void orderByRecommendTest(){
        //given
        Long volumeId = volumeService.createVolume(10, 10, 4L);
        Volume volume = volumeService.findOne(volumeId);
        Long volumeId2 = volumeService.createVolume(10, 10, 3L);
        Volume volume2 = volumeService.findOne(volumeId2);
        Long testId = routineService.createRoutine(1L, "테스트Test1A", ExercisePart.BACK, true, volume);
        Long testId2 = routineService.createRoutine(1L, "테스트Test2B", ExercisePart.CALVES, true, volume2);
        routineService.recommend(testId , 2L);
        routineService.recommend(testId , 1L);
        routineService.recommend(6L , 1L);
        //when
        List<Routine> shareRoutineRecommend = routineRepository.findShareRoutineRecommend(0, 10, "desc");
        for (Routine routine : shareRoutineRecommend) {
            System.out.println("routine = " + routine.getId() +" "+ routine.getTitle());
            System.out.println("name = " + routine.getMember().getName());
        }
        //then

    }



}
