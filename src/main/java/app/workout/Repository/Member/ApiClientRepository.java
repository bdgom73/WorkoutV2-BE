package app.workout.Repository.Member;

import app.workout.Entity.Member.ApiClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApiClientRepository extends JpaRepository<ApiClient, Long> {

    @Query("SELECT ac FROM ApiClient ac JOIN FETCH ac.member WHERE ac.member.id = :memberId")
    Optional<ApiClient> findByMember(@Param("memberId") Long memberId);

    Optional<ApiClient> findByApiKey(String apiKey);
}
