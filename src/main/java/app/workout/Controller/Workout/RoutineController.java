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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static app.workout.Service.CustomPageRequest.getPageRequest;

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
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "createDate") String sortString,
            @RequestParam(name = "direction", defaultValue = "aes") String direction
    ){
        List<RoutineResponse> result;
        if(Objects.equals(sortString, "recommend")){
            result = routineService.findRoutineOrderByRecommend(page, size, direction)
                    .stream()
                    .map(RoutineResponse::new)
                    .collect(Collectors.toList());
        }else{
            PageRequest pageRequest = getPageRequest(page, size, sortString, direction);
            result = routineService.findAll(pageRequest).stream()
                    .map(RoutineResponse::new).collect(Collectors.toList());
        }

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
    public ReturnTypeV1<Long> editRoutine(
            @PathVariable("routineId") Long routineId,
            @RequestBody EditRoutineRequest routineRequest,
            @Login Long memberId
    ){
        Long rId = routineService.editRoutine(routineId, memberId, routineRequest.getTitle(), routineRequest.getPart());
        return new ReturnTypeV1<>(rId);
    }

    @DeleteMapping("/routines/{routineId}")
    public void deleteRoutine(@Login Long loginId, @PathVariable("routineId") Long routineId){
        routineService.deleteRoutine(routineId,loginId);
    }

    // 공유루틴
    @GetMapping("/routines/share")
    public ReturnTypeV1<List<RoutineResponse>> shareRoutines(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(name = "sort", defaultValue = "createDate") String sortString,
            @RequestParam(name = "direction", defaultValue = "AES") String direction
    ){
        PageRequest pageRequest = getPageRequest(page, size, sortString, direction);
        List<RoutineResponse> result = routineService.findByShare(pageRequest)
                .stream().map(RoutineResponse::new).collect(Collectors.toList());
        return new ReturnTypeV1<>(result);
    }

    // 복사
    @PostMapping("/routines/{routineId}/copy")
    public ReturnTypeV1<Long> copyRoutines(@PathVariable("routineId") Long routineId, @Login Long memberId){
        Long copyRoutineId = routineService.copyRoutine(routineId, memberId);
        return new ReturnTypeV1<>(copyRoutineId);
    }

    // 추천
    @PostMapping("/routines/{routineId}/recommend")
    public void recommendRoutine(@Login Long memberId, @PathVariable("routineId") Long routineId){
        routineService.recommend(routineId, memberId);
    }

    @DeleteMapping("/routines/{routineId}/recommend")
    public void deleteRecommendRoutine(@Login Long memberId, @PathVariable("routineId") Long routineId){
        routineService.recommendCancel(routineId,memberId);
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
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;
        public RoutineResponse(Routine routine) {
            this.routineId = routine.getId();
            this.title = routine.getTitle();
            this.part = routine.getPart();
            this.share = routine.isShare();
            this.originalAuthor = routine.getOriginalAuthor();
            this.memberId = routine.getMember().getId();
            this.memberNickname = routine.getMember().getNickname();
            this.createdDate = routine.getCreatedDate();
            this.modifiedDate =routine.getModifiedDate();
        }
    }

}
