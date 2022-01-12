package app.workout.Entity.Member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBodyData is a Querydsl query type for BodyData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBodyData extends EntityPathBase<BodyData> {

    private static final long serialVersionUID = -1517143757L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBodyData bodyData = new QBodyData("bodyData");

    public final app.workout.Entity.QBaseEntity _super = new app.workout.Entity.QBaseEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Double> height = createNumber("height", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QBodyData(String variable) {
        this(BodyData.class, forVariable(variable), INITS);
    }

    public QBodyData(Path<? extends BodyData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBodyData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBodyData(PathMetadata metadata, PathInits inits) {
        this(BodyData.class, metadata, inits);
    }

    public QBodyData(Class<? extends BodyData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

