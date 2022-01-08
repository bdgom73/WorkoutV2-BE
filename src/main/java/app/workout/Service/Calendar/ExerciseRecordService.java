package app.workout.Service.Calendar;

import app.workout.Entity.Calendar.ExerciseRecord;
import app.workout.Entity.Member.Member;
import app.workout.Repository.ExerciseRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseRecordService {

    private final ExerciseRecordRepository exerciseRecordRepository;

    /**
     * 범위안의 운동기록 찾기
     * */
    public List<ExerciseRecord> rangeExerciseRecode(Long memberId, LocalDate start, LocalDate end){
        return exerciseRecordRepository.findRangeByMember(memberId, start, end);
    }

    /**
     * 특정 운동기록 찾기
     * */
    public ExerciseRecord getExerciseRecord(Long exerciseId){
        return exerciseRecordRepository.findById(exerciseId).orElseThrow(()-> {throw new IllegalStateException("존재하지 않는 기록입니다");});
    }

    /**
     * 운동기록생성
     * */
    @Transactional
    public Long createExerciseRecode(Member member, LocalDate date, String memo, boolean isWorkout){
        ExerciseRecord record = new ExerciseRecord(isWorkout,date,memo);
        record.setMember(member);
        exerciseRecordRepository.save(record);
        return record.getId();
    }

    /**
     * 운동기록 전반 수정
     * */
    @Transactional
    public Long updateExerciseRecode(Long exerciseRecodeId, LocalDate date, String memo, boolean isWorkout){
        ExerciseRecord record = exerciseRecordRepository.findById(exerciseRecodeId).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않은 기록입니다");
        });
        record.changeExerciseRecode(isWorkout,date,memo);
        return record.getId();
    }

    /**
     * 운동여부 수정
     * */
    @Transactional
    public void updateWorkoutState(Long exerciseRecodeId, boolean isWorkout){
        ExerciseRecord record = exerciseRecordRepository.findById(exerciseRecodeId).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않은 기록입니다");
        });
        record.changeWorkoutState(isWorkout);
    }

    /**
     * 운동기록 삭제
     * */
    @Transactional
    public void deleteExerciseRecode(Long exerciseRecodeId){
        exerciseRecordRepository.deleteById(exerciseRecodeId);
    }
}
