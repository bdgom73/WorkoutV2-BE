package app.workout.Entity.Workout;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkout is a Querydsl query type for Workout
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkout extends EntityPathBase<Workout> {

    private static final long serialVersionUID = 543515647L;

    public static final QWorkout workout = new QWorkout("workout");

    public final app.workout.Entity.QBaseEntity _super = new app.workout.Entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final EnumPath<app.workout.Entity.Workout.Eunm.ExercisePart> part = createEnum("part", app.workout.Entity.Workout.Eunm.ExercisePart.class);

    public final EnumPath<app.workout.Entity.Workout.Eunm.ExerciseType> type = createEnum("type", app.workout.Entity.Workout.Eunm.ExerciseType.class);

    public QWorkout(String variable) {
        super(Workout.class, forVariable(variable));
    }

    public QWorkout(Path<? extends Workout> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkout(PathMetadata metadata) {
        super(Workout.class, metadata);
    }

}

