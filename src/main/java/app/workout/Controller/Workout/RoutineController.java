package app.workout.Controller.Workout;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Service.ArgumentResolver.Login.Login;
import app.workout.Service.Routine.CreateVolumeDTO;
import app.workout.Service.Routine.RoutineService;
import app.workout.Service.Routine.VolumeService;
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
    private final VolumeService volumeService;

    @GetMapping("/routines")
    public ReturnTypeV1<List<RoutineResponse>> routines(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        List<RoutineResponse> result = routineService.findAll(PageRequest.of(page, size)).stream()
                .map(RoutineResponse::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    @GetMapping("/routines/{routineId}")
    public ReturnTypeV1<RoutineResponse> getRoutine(@PathVariable("routineId") Long routineId){
        Routine routine = routineService.findOne(routineId);
        return new ReturnTypeV1<>(new RoutineResponse(routine));
    }

    @PostMapping("/routines")
    public ReturnTypeV1<Long> addRoutine(@Login Long memberId, @RequestBody RoutineRequest routineRequest){
        List<Volume> volumeV2 = volumeService.createVolumeV2(routineRequest.getVolumes());
        Long routineId = routineService.createRoutine(memberId, routineRequest.getTitle(), routineRequest.getPart(), routineRequest.getShare(), volumeV2);
        return new ReturnTypeV1<>(routineId);
    }

    @PutMapping("/routines/{routineId}")
    public ReturnTypeV1<Long> editRoutine(@PathVariable("routineId") Long routineId, @RequestBody EditRoutineRequest routineRequest){
        Long rId = routineService.editRoutine(routineId, routineRequest.getTitle(), routineRequest.getPart());
        return new ReturnTypeV1<>(rId);
    }

    @DeleteMapping("/routines/{routineId}")
    public void deleteRoutine(@Login Long loginId, @PathVariable("routineId") Long routineId){
        routineService.deleteRoutine(routineId,loginId);
    }

    @Data
    private static class EditRoutineRequest{
        private String title;
        private ExercisePart part;
    }

    @Data
    private static class RoutineRequest{
        private String title;
        private ExercisePart part;
        private Boolean share;
        private List<CreateVolumeDTO> volumes;
    }


    @Data
    private static class RoutineResponse {
        private Long routineId;
        private String title;
        private ExercisePart part;
        private boolean share;
        private Long originalAuthor;
        private Long memberId;
        private String memberNickname;

        public RoutineResponse(Routine routine) {
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
