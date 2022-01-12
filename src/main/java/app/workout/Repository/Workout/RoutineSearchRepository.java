package app.workout.Repository.Workout;

import app.workout.Entity.Workout.Routine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoutineSearchRepository {
    List<Routine> findSearchByTitle(String title);
    List<Routine> findSearchByTitle(String title, Pageable pageable);
    List<Routine> findSearchByTitleAndShare(String title);
    List<Routine> findSearchByTitleAndShare(String title, Pageable pageable);
}
