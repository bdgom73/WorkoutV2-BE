package app.workout.Repository.Workout;

import app.workout.Entity.Workout.Routine;
import app.workout.Entity.Workout.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VolumeRepository extends JpaRepository<Volume, Long> {

    @Query("SELECT v FROM Volume v WHERE v.routine.id = :routineId")
    List<Volume> findByRoutine(Long routineId);

    @Query("SELECT v FROM Volume v JOIN FETCH v.workout w WHERE v.id = :volumeId")
    Optional<Volume> findOne(Long volumeId);


}
