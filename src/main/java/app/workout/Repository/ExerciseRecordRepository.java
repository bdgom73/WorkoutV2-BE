package app.workout.Repository;

import app.workout.Entity.Calendar.ExerciseRecord;
import app.workout.Entity.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {

    @Query("SELECT e FROM ExerciseRecord e " +
            "WHERE e.member.id=:memberId AND e.date BETWEEN :start AND :end")
    List<ExerciseRecord> findRangeByMember(
            @Param("memberId") Long memberId, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
