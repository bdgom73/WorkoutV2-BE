package app.workout.Entity.Workout;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendation is a Querydsl query type for Recommendation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendation extends EntityPathBase<Recommendation> {

    private static final long serialVersionUID = 294595607L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendation recommendation = new QRecommendation("recommendation");

    public final app.workout.Entity.QBaseEntity _super = new app.workout.Entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final app.workout.Entity.Member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final BooleanPath recommended = createBoolean("recommended");

    public final QRoutine routine;

    public QRecommendation(String variable) {
        this(Recommendation.class, forVariable(variable), INITS);
    }

    public QRecommendation(Path<? extends Recommendation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendation(PathMetadata metadata, PathInits inits) {
        this(Recommendation.class, metadata, inits);
    }

    public QRecommendation(Class<? extends Recommendation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new app.workout.Entity.Member.QMember(forProperty("member")) : null;
        this.routine = inits.isInitialized("routine") ? new QRoutine(forProperty("routine"), inits.get("routine")) : null;
    }

}

