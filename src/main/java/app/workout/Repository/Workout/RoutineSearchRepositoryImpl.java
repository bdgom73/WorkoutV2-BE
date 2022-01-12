package app.workout.Repository.Workout;

import app.workout.Entity.Workout.QRoutine;
import app.workout.Entity.Workout.Routine;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class RoutineSearchRepositoryImpl implements RoutineSearchRepository{

    private final EntityManager em;
    JPAQueryFactory factory = new JPAQueryFactory(em);

    @Override
    public List<Routine> findSearchByTitle(String title) {
        QRoutine routine = QRoutine.routine;
        return factory.selectFrom(routine)
                .where(routine.title.contains(title))
                .fetch();
    }

    @Override
    public List<Routine> findSearchByTitle(String title, Pageable pageable) {
        return null;
    }

    @Override
    public List<Routine> findSearchByTitleAndShare(String title) {
        return null;
    }

    @Override
    public List<Routine> findSearchByTitleAndShare(String title, Pageable pageable) {
        return null;
    }
}
