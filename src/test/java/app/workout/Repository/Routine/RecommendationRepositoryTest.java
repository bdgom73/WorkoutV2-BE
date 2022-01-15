package app.workout.Repository.Routine;

import app.workout.Entity.Workout.Recommendation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RecommendationRepositoryTest {

    @Autowired
    RecommendationRepository recommendationRepository;

    @Test
    void recommendationTest(){
       recommendationRepository.findByRoutineAndMember(6L, 1L);

    }
}