package app.workout.Repository.Routine;

import app.workout.Entity.Member.Member;
import app.workout.Entity.Workout.Recommendation;
import app.workout.Entity.Workout.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    Optional<Recommendation> findByRoutineAndMember(Routine routine, Member member);
    @Query("SELECT r FROM Recommendation r" +
            " WHERE r.routine.id = :routineId AND" +
            " r.member.id = :memberId")
    Optional<Recommendation> findByRoutineAndMember(@Param("routineId") Long routineId, @Param("memberId") Long memberId);
}
