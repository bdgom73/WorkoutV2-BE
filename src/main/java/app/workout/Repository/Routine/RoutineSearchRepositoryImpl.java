package app.workout.Repository.Routine;

import app.workout.Entity.Workout.QRoutine;
import app.workout.Entity.Workout.Routine;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class RoutineSearchRepositoryImpl implements RoutineSearchRepository{

    private final EntityManager em;
    JPAQueryFactory query;

    @Override
    public List<Routine> findSearchByTitle(String title) {
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");
        return query.selectFrom(r)
                .where(r.title.contains(title))
                .fetch();
    }

    @Override
    public List<Routine> findSearchByTitle(String title, Pageable pageable) {
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");
        PathBuilder<Routine> routine = new PathBuilder<>(Routine.class, "routine");

        Sort sort = pageable.getSort();
        for (Sort.Order order : sort) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

        }

        return query.select(r)
                .from(r)
                .where(r.title.contains(title))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    @Override
    public List<Routine> findSearchByTitleAndShare(String title) {
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");
        return query.selectFrom(r)
                .where(r.title.contains(title).and(r.share.eq(Boolean.TRUE)))
                .fetch();
    }

    @Override
    public List<Routine> findSearchByTitleAndShare(String title, Pageable pageable) {
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");
        return query.selectFrom(r)
                .where(r.title.contains(title).and(r.share.eq(Boolean.TRUE)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

    @Override
    public List<Routine> findShareRoutine(Pageable pageable){
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");

        return query.select(r)
                .from(r)
                .where(r.share.eq(Boolean.TRUE))
                .orderBy(r.createdDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }
    @Override
    public List<Routine> findShareRoutine() {
        query = new JPAQueryFactory(em);
        QRoutine r = new QRoutine("r");
        return query.select(r)
                .from(r)
                .where(r.share.eq(Boolean.TRUE))
                .orderBy(r.createdDate.desc())
                .limit(0)
                .offset(100)
                .fetch();

    }
}
