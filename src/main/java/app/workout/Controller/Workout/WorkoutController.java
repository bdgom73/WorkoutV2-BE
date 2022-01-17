package app.workout.Controller.Workout;

import app.workout.Controller.ReturnType.ReturnTypeV1;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Eunm.ExerciseType;
import app.workout.Entity.Workout.Workout;
import app.workout.Service.CustomPageRequest;
import app.workout.Service.Workout.WorkoutService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workouts")
    public ReturnTypeV1<List<WorkoutResponse>> workouts(
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sortString,
            @RequestParam(name = "direction", defaultValue = "aes") String direction
    ){
        PageRequest pageRequest = CustomPageRequest.getPageRequest(page, size, sortString, direction);
        List<WorkoutResponse> result = workoutService.findAll(pageRequest).stream().map(w ->
                new WorkoutResponse(w.getId(), w.getName(), w.getImageUrl(), w.getPart(), w.getType(), w.getExplanation())
        ).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    @GetMapping("/workouts/{workoutId}")
    public ReturnTypeV1<WorkoutResponse> getWorkout(@PathVariable("workoutId") Long workoutId){
        Workout workout = workoutService.findOne(workoutId);
        return new ReturnTypeV1<>(new WorkoutResponse(workout.getId(), workout.getName(),workout.getImageUrl() ,workout.getPart(), workout.getType(), workout.getExplanation()));
    }

    @PostMapping("/workouts")
    public ReturnTypeV1<Long> addWorkout(@RequestBody WorkoutRequest workoutRequest){
        Long wId = workoutService.addWorkout(workoutRequest.getName(), workoutRequest.getPart(), workoutRequest.getType());
        return new ReturnTypeV1<>(wId);
    }

    @PutMapping("/workouts/{workoutId}")
    public ReturnTypeV1<EditWorkoutResponse> editWorkout(@PathVariable("workoutId") Long workoutId , @RequestBody EditWorkoutRequest workoutRequest){
        Workout workout = workoutService.editWorkout(workoutId, workoutRequest.getName(), workoutRequest.getPart(), workoutRequest.getType(), workoutRequest.getExplanation());
        return new ReturnTypeV1<>(new EditWorkoutResponse(workout.getId(), workout.getName(), workout.getImageUrl(), workout.getPart(), workout.getType(), workout.getExplanation()));
    }

    @DeleteMapping("/workouts/{workoutId}")
    public void deleteWorkout(@PathVariable("workoutId") Long workoutId){
        workoutService.deleteWorkout(workoutId);
    }

    @Data
    private static class EditWorkoutRequest{
        @NotBlank
        private String name;
        @NotBlank
        private ExercisePart part;
        @NotBlank
        private ExerciseType type;
        @NotBlank
        private String explanation;

        public EditWorkoutRequest(String name, ExercisePart part, ExerciseType type, String explanation) {
            this.name = name;
            this.part = part;
            this.type = type;
            this.explanation = explanation;
        }
    }

    @Data
    private static class EditWorkoutResponse{
        private Long workoutId;
        private String name;
        private String imageUrl;
        private ExercisePart part;
        private ExerciseType type;
        private String explanation;

        public EditWorkoutResponse(Long workoutId, String name, String imageUrl, ExercisePart part, ExerciseType type, String explanation) {
            this.workoutId = workoutId;
            this.name = name;
            this.imageUrl = imageUrl;
            this.part = part;
            this.type = type;
            this.explanation = explanation;
        }
    }

    @Data
    private static class WorkoutRequest{
        @NotBlank
        private String name;
        @NotBlank
        private ExercisePart part;
        @NotBlank
        private ExerciseType type;

        public WorkoutRequest(String name, ExercisePart part, ExerciseType type) {
            this.name = name;
            this.part = part;
            this.type = type;
        }
    }

    @Data
    private static class WorkoutResponse {
        private Long workoutId;
        private String name;
        private String imageUrl;
        private ExercisePart part;
        private ExerciseType type;
        private String explanation;

        public WorkoutResponse(Long workoutId, String name, String imageUrl, ExercisePart part, ExerciseType type, String explanation) {
            this.workoutId = workoutId;
            this.name = name;
            this.imageUrl = imageUrl;
            this.part = part;
            this.type = type;
            this.explanation = explanation;
        }
    }

}
