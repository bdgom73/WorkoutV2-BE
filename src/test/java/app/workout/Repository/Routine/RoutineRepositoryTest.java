package app.workout.Repository.Routine;

import app.workout.Entity.Workout.Routine;
import com.querydsl.core.types.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoutineRepositoryTest {

    @Autowired
    RoutineRepository routineRepository;

    @Test
    void searchTest(){
        List<Routine> test = routineRepository.findSearchByTitle("test");
        for (Routine routine : test) {
            System.out.println("routine.getTitle() = " + routine.getTitle());
        }
    }

    @Test
    void routineSortTest(){
        PageRequest pageRequest = PageRequest.of(0, 30, Sort.by("title").descending());

        List<Routine> t = routineRepository.findSearchByTitle("t", pageRequest);
        for (Routine routine : t) {
            System.out.println("routine = " + routine.getTitle());
        }

    }
    @Test
    public void test(){
        String direction = "desc";
        PageRequest.of(20,30, Sort.Direction.valueOf(direction),"member");
    }
}