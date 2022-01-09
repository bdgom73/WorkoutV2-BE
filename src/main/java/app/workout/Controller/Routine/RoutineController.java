package app.workout.Controller.Routine;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Routine;
import app.workout.Repository.RoutineRepository;
import app.workout.Repository.WorkoutRepository;
import app.workout.Service.Routine.RoutineService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoutineController {

    private final RoutineService routineService;
    private final RoutineRepository routineRepository;
    private final WorkoutRepository workoutRepository;

    @GetMapping("/routines")
    public ReturnTypeV1<List<SingleRoutineResponse>> getRoutines(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        List<SingleRoutineResponse> result = routineService.findAll(PageRequest.of(page, size)).stream()
                .map(SingleRoutineResponse::new).collect(Collectors.toList());
        ReturnTypeV1<List<SingleRoutineResponse>> listReturnTypeV1 = new ReturnTypeV1<>(result);
        return listReturnTypeV1;
    }

    @GetMapping("/routines/{routineId}")
    public void routineList(@PathVariable("routineId") Long routineId){
        Routine routine = routineService.findOne(routineId);
//        return new ReturnTypeV1<>()
    }

    @Data
    private static class SingleRoutineResponse{
        private Long routineId;
        private String title;
        private ExercisePart part;
        private boolean share;
        private Long originalAuthor;
        private Long memberId;
        private String memberNickname;

        public SingleRoutineResponse(Routine routine) {
            this.routineId = routine.getId();
            this.title = routine.getTitle();
            this.part = routine.getPart();
            this.share = routine.isShare();
            this.originalAuthor = routine.getOriginalAuthor();
            this.memberId = routine.getMember().getId();
            this.memberNickname = routine.getMember().getNickname();
        }
    }

}
