package app.workout.Entity.Calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseRecord is a Querydsl query type for ExerciseRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseRecord extends EntityPathBase<ExerciseRecord> {

    private static final long serialVersionUID = -2007925268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseRecord exerciseRecord = new QExerciseRecord("exerciseRecord");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isWorkout = createBoolean("isWorkout");

    public final app.workout.Entity.Member.QMember member;

    public final StringPath memo = createString("memo");

    public QExerciseRecord(String variable) {
        this(ExerciseRecord.class, forVariable(variable), INITS);
    }

    public QExerciseRecord(Path<? extends ExerciseRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseRecord(PathMetadata metadata, PathInits inits) {
        this(ExerciseRecord.class, metadata, inits);
    }

    public QExerciseRecord(Class<? extends ExerciseRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new app.workout.Entity.Member.QMember(forProperty("member")) : null;
    }

}

