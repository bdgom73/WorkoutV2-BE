package app.workout.Repository.Workout;

import app.workout.Entity.Workout.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Query("SELECT r FROM Routine r JOIN FETCH r.member m WHERE m.id=:memberId")
    List<Routine> findAllByMember(@Param("memberId") Long memberId, Pageable pageable); // 해당 유저의 루틴 리스트 가져오기

    @Query("SELECT r FROM Routine r JOIN FETCH r.member m")
    List<Routine> findAllByMemberFetch(Pageable pageable); // 루틴 , 유저 매핑해서 리스트 가져오기

    @Query("SELECT r FROM Routine r JOIN FETCH r.member m WHERE r.id=:routineId")
    Optional<Routine> findMemberFetch(Long routineId); // 해당 루틴의 유저 매핑 후 가져오기
}
