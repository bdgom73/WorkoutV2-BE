package app.workout.Service.Routine;

import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Entity.Workout.Workout;
import app.workout.Repository.VolumeRepository;
import app.workout.Service.Workout.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VolumeService {

    private final VolumeRepository volumeRepository;
    private final WorkoutService workoutService;
    private final RoutineService routineService;

    /**
     * 볼륨 검색
     * @param volumeId 볼륨 고유 아이디
     * @return Volume
     * @throws IllegalStateException 볼륨을 찾을 수 없습니다
     * */
    public Volume findOne(Long volumeId){
        return volumeRepository.findById(volumeId).orElseThrow(()->{throw new IllegalStateException("볼륨을 찾을 수 없습니다");});
    }

    /**
     * 해당 루틴의 볼륨 전체가져오기
     * @param routineId 루틴 고유 아이디
     * @return List<Volume>
     * */
    public List<Volume> findVolumeByRoutine(Long routineId){
        return volumeRepository.findByRoutine(routineId);
    }

    /**
     * 볼륨의 운동까지 한번에 가져오기
     * @param volumeId 볼륨 고유 아이디
     * @return Volume
     * @throws IllegalStateException 볼륨을 찾을 수 없습니다
     * */
    public Volume findOneWithWorkout(Long volumeId){
        return volumeRepository.findOne(volumeId).orElseThrow(()->{throw new IllegalStateException("볼륨을 찾을 수 없습니다");});
    }

    /**
     * 단일 볼륨추가 v1
     * @param num 세트당 운동 수
     * @param sets 세트수
     * @param workout 운동종목
     * @return Long
     * */
    @Transactional
    public Long createVolume(int num, int sets, Workout workout){
        Volume volume = Volume.createVolume(num, sets, workout);
        volumeRepository.save(volume);
        return volume.getId();
    }

    /**
     * 단일 볼륨추가 v1.1
     * @param num 세트당 운동 수
     * @param sets 세트수
     * @param workoutId 운동 고유 아이디
     * @return Long
     * */
    @Transactional
    public Long createVolume(int num, int sets, Long workoutId){
        Workout workout = workoutService.findOne(workoutId);
        Volume volume = Volume.createVolume(num, sets, workout);

        volumeRepository.save(volume);
        return volume.getId();
    }

    /**
     *  다중 볼륨추가
     * @param volumeDTOS num, sets, workoutId List
     * @return List<Long>
     * */
    @Transactional
    public List<Long> createVolume(List<CreateVolumeDTO> volumeDTOS){
        return volumeDTOS.stream().map(v -> createVolume(v.getNum(), v.getSets(), v.getWorkoutId())).collect(Collectors.toList());
    }
    @Transactional
    public List<Volume> createVolumeV2(List<CreateVolumeDTO> volumeDTOS){
        List<Volume> volumes = new ArrayList<>();
        volumeDTOS.forEach(v -> {
            Workout workout = workoutService.findOne(v.getWorkoutId());
            volumes.add(Volume.createVolume(v.getNum(), v.getSets(), workout));
        });
        return volumes;
    }

    @Transactional
    public Long editVolume(Long volumeId, Long  workoutId, int num, int sets){
        Workout workout = workoutService.findOne(workoutId);
        Volume volume = findOne(volumeId);
        volume.changeVolume(num,sets);
        volume.setWorkout(workout);
        return volume.getId();
    }

    @Transactional
    public void deleteVolume(Long volumeId){
        volumeRepository.deleteById(volumeId);
    }

}
