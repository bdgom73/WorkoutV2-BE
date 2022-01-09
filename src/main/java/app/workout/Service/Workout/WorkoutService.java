package app.workout.Service.Workout;

import app.workout.Entity.Workout.Workout;
import app.workout.Repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public Workout findOne(Long workoutId){
        return workoutRepository.findById(workoutId).orElseThrow(() -> {
            throw new IllegalStateException("등록되지않은 운동입니다");
        });
    }
}
