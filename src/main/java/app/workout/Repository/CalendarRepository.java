package app.workout.Repository;

import app.workout.Entity.Calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar,Long> {

   @Query("SELECT c FROM Calendar c WHERE c.member.id = :memberId" +
           " AND c.startDate BETWEEN :startDate AND :endDate")
   List<Calendar> findRangeByMember(@Param("memberId") Long memberId, @Param("startDate") LocalDate startDate ,@Param("endDate") LocalDate endDate);
}
