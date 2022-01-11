package app.workout.Service.Workout;

import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Eunm.ExerciseType;
import app.workout.Entity.Workout.Workout;
import app.workout.Repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    /**
     * 운동 상세정보
     * */
    public Workout findOne(Long workoutId){
        return workoutRepository.findById(workoutId).orElseThrow(() -> {
            throw new IllegalStateException("등록되지않은 운동입니다");
        });
    }

    /**
     * 운동 리스트
     * */
    public List<Workout> findAll(Pageable pageable){
        return workoutRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    /**
     * 운동 추가
     * */
    @Transactional
    public Long addWorkout(String name, ExercisePart part, ExerciseType type){
        Workout workout = new Workout(name,part,type);
        workoutRepository.save(workout);
        return workout.getId();
    }

    /**
     * 운동 수정 (이름, 운동 부위, 운동 타입)
     * */
    @Transactional
    public Workout editWorkout(Long workoutId,String name, ExercisePart part, ExerciseType type){
        Workout workout = findOne(workoutId);
        workout.changeWorkout(name,part,type);
        return workout;
    }
    @Transactional
    public Workout editWorkout(Long workoutId,String name, ExercisePart part, ExerciseType type, String explanation){
        Workout workout = findOne(workoutId);
        workout.changeWorkout(name,part,type);
        workout.changeExplanation(explanation);
        return workout;
    }
    /**
     * 운동 수정 (설명)
     * */
    @Transactional
    public void editExplanation(Long workoutId, String explanation){
        Workout workout = findOne(workoutId);
        workout.changeExplanation(explanation);
    }

    /**
     * 운동 삭제
     * */
    @Transactional
    public void deleteWorkout(Long workoutId){
        workoutRepository.deleteById(workoutId);
    }
}
