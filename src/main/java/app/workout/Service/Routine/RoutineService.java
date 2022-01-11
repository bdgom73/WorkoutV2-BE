package app.workout.Service.Routine;

import app.workout.Entity.Member.Member;
import app.workout.Entity.Workout.Eunm.ExercisePart;
import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import app.workout.Entity.Workout.Workout;
import app.workout.Repository.RoutineRepository;
import app.workout.Service.Member.MemberService;
import app.workout.Service.Workout.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final MemberService memberService;
    private final WorkoutService workoutService;

    /**
     * 루틴 검색
     * */
    public Routine findOne(Long routineId){
        return routineRepository.findById(routineId).orElseThrow(()->{
            throw new IllegalStateException("루틴 정보를 찾을 수 없습니다.");
        });
    }
    /**
     * 루틴(루틴, 유저) 검색
     * */
    public Routine findByMember(Long routineId){
       return routineRepository.findMemberFetch(routineId).orElseThrow(()->{
           throw new IllegalStateException("루틴 정보를 찾을 수 없습니다.");
       });
    }

    public List<Routine> findAll(Pageable pageable){
        return routineRepository.findAllByMemberFetch(pageable);
    }

    /**
     * 특정유저의 루틴 가져오기
     * */
    public List<Routine> findAllByMember(Long memberId ,Pageable pageable){
        return routineRepository.findAllByMember(memberId,pageable);
    }

    /**
     * 해당 루틴 정보(루틴, 볼륨, 운동) 연관관계 전부 매핑해서 가져오기
     * */
    public Routine findRoutine(Long routineId){
        Routine routine = findOne(routineId);
        for (Volume volume : routine.getVolumes()) {
            volume.getWorkout().getName();
        }
        return routine;
    }

    /**
     * 루틴 생성
     * */
    @Transactional
    public Long createRoutine(Long memberId, String title, ExercisePart part, boolean share, List<Volume> volumes){
        Member member = memberService.findOne(memberId);
        Routine routine = Routine.createRoutine(title, part, share, member, volumes);
        routineRepository.save(routine);
        return routine.getId();
    }
    @Transactional
    public Long createRoutine(Long memberId, String title, ExercisePart part, boolean share, Volume... volumes){
        Member member = memberService.findOne(memberId);
        Routine routine = Routine.createRoutine(title, part, share, member, volumes);
        routineRepository.save(routine);
        return routine.getId();
    }
    /**
     * 루틴에 볼륨 추가
     * */
    @Transactional
    public Long addVolume(Long routineId, Long workoutId, int num , int sets){
        Workout workout = workoutService.findOne(workoutId);
        Routine routine = findOne(routineId);
        routine.addVolumes(Volume.createVolume(num,sets,workout));
        return routine.getId();
    }
    /**
     * 루틴의 제목과 부위 수정
     * */
    @Transactional
    public Long editRoutine(Long routineId, String title, ExercisePart part){
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> {
            throw new IllegalStateException("찾을 수 없는 루틴입니다");
        });
        routine.changeRoutine(title,part);
        return routine.getId();
    }

    @Transactional
    public void deleteRoutine(Long routineId){
        routineRepository.deleteById(routineId);
    }
    @Transactional
    public void deleteRoutine(Long routineId, Long memberId){
        Routine routine = findByMember(routineId);
        if(!Objects.equals(routine.getMember().getId(), memberId)){
            throw new IllegalStateException("권한이 없습니다");
        }
        routineRepository.delete(routine);
    }
}
