package app.workout.Entity.Workout;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVolume is a Querydsl query type for Volume
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVolume extends EntityPathBase<Volume> {

    private static final long serialVersionUID = 2066944376L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVolume volume = new QVolume("volume");

    public final app.workout.Entity.QBaseEntity _super = new app.workout.Entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final QRoutine routine;

    public final NumberPath<Integer> sets = createNumber("sets", Integer.class);

    public final QWorkout workout;

    public QVolume(String variable) {
        this(Volume.class, forVariable(variable), INITS);
    }

    public QVolume(Path<? extends Volume> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVolume(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVolume(PathMetadata metadata, PathInits inits) {
        this(Volume.class, metadata, inits);
    }

    public QVolume(Class<? extends Volume> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routine = inits.isInitialized("routine") ? new QRoutine(forProperty("routine"), inits.get("routine")) : null;
        this.workout = inits.isInitialized("workout") ? new QWorkout(forProperty("workout")) : null;
    }

}

