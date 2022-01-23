package app.workout.Repository.Member;

import app.workout.Entity.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberSearchRepository {

    Optional<Member> findByEmail(String email);

    @Query("SELECT m FROM Member m JOIN FETCH m.bodyData b")
    List<Member> findBodyAll();

    @Query("SELECT m FROM Member m JOIN FETCH m.apiClient a WHERE m.id = :memberId")
    Optional<Member> findKeyById(@Param("memberId") Long memberId);
}
