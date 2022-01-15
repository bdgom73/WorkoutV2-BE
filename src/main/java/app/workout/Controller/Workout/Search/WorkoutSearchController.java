package app.workout.Controller.Workout.Search;

import app.workout.Service.Routine.RoutineService;
import app.workout.Service.Routine.VolumeService;
import app.workout.Service.Workout.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 운동관련 검색 컨트롤러
 * */
@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkoutSearchController {

    private final WorkoutService workoutService;
    private final VolumeService volumeService;
    private final RoutineService routineService;


}
