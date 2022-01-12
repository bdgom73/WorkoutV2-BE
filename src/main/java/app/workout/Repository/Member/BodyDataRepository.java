package app.workout.Repository.Member;

import app.workout.Entity.Member.BodyData;
import app.workout.Entity.Member.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BodyDataRepository extends JpaRepository<BodyData, Long> {

    @Query("SELECT b FROM BodyData b WHERE b.member.id=:memberId ORDER BY b.id DESC")
    List<BodyData> findByMember(@Param("memberId") Long memberId, Pageable pageable);
    @Query("SELECT b FROM BodyData b WHERE b.member.id=:memberId ORDER BY b.id DESC")
    List<BodyData> findByMember(@Param("memberId") Long memberId);
}
