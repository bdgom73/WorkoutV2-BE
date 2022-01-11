package app.workout.Controller.Workout;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Eunm.ExerciseType;
import app.workout.Entity.Workout.Volume;
import app.workout.Service.Routine.RoutineService;
import app.workout.Service.Routine.VolumeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class VolumeController {

    private final VolumeService volumeService;
    private final RoutineService routineService;

    @GetMapping("/volumes")
    public ReturnTypeV1<List<FullVolumeResponse>> volumes(@RequestParam(name = "routineId") Long routineId){
        List<FullVolumeResponse> result = volumeService.findVolumeByRoutine(routineId).stream().map(FullVolumeResponse::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    @GetMapping("/volumes/{volumeId}")
    public ReturnTypeV1<FullVolumeResponse> getVolume(@PathVariable("volumeId") Long volumeId){
        Volume volume = volumeService.findOneWithWorkout(volumeId);
        FullVolumeResponse fullVolumeResponse = new FullVolumeResponse(volume);
        return new ReturnTypeV1<>(fullVolumeResponse);
    }

    @PostMapping("/volumes")
    public void addVolumes(@RequestBody SimpleVolumeRequestV1 volumeRequest){
        routineService.addVolume(volumeRequest.getRoutineId(), volumeRequest.getWorkoutId(), volumeRequest.getNum(), volumeRequest.getSets());
    }

    @PutMapping("/volumes/{volumeId}")
    public ReturnTypeV1<Long> editVolumes(@PathVariable("volumeId") Long volumeId, @RequestBody SimpleVolumeRequestV2 volumeRequest){
        Long vId = volumeService.editVolume(volumeId, volumeRequest.getWorkoutId(), volumeRequest.getNum(), volumeRequest.getSets());
        return new ReturnTypeV1<>(vId);
    }

    @DeleteMapping("/volumes/{volumeId}")
    public void deleteVolumes(@PathVariable("volumeId") Long volumeId){
        volumeService.deleteVolume(volumeId);
    }

    @Data
    private static class SimpleVolumeRequestV1 {
        @NotBlank
        private Long routineId;
        @NotBlank
        private Long workoutId;
        @NotBlank
        private int num;
        @NotBlank
        private int sets;

        public SimpleVolumeRequestV1(Long routineId, Long workoutId, int num, int sets) {
            this.routineId = routineId;
            this.workoutId = workoutId;
            this.num = num;
            this.sets = sets;
        }
    }
    @Data
    private static class SimpleVolumeRequestV2 {
        @NotBlank
        private Long workoutId;
        @NotBlank
        private int num;
        @NotBlank
        private int sets;

        public SimpleVolumeRequestV2( Long workoutId, int num, int sets) {
            this.workoutId = workoutId;
            this.num = num;
            this.sets = sets;
        }
    }
    @Data
    private static class FullVolumeResponse{
        private Long volumeId;
        private int num;
        private int sets;

        private Long workoutId;
        private String workoutName;
        private String workoutImageUrl;
        private ExercisePart workoutPart;
        private ExerciseType workoutType;

        private Long routineId;

        public FullVolumeResponse(Volume volume) {
            this.volumeId = volume.getId();
            this.num = volume.getNum();
            this.sets = volume.getSets();
            this.workoutId = volume.getWorkout().getId();
            this.workoutName = volume.getWorkout().getName();
            this.workoutImageUrl = volume.getWorkout().getImageUrl();
            this.workoutPart = volume.getWorkout().getPart();
            this.workoutType = volume.getWorkout().getType();
            this.routineId = volume.getRoutine().getId();
        }
    }
}
