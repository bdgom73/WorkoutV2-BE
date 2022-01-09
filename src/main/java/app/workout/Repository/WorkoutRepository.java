package app.workout.Repository;

import app.workout.Entity.Workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout,Long> {

}
