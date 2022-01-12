package app.workout.Repository.Workout;

import app.workout.Entity.Workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout,Long> {

}
