package app.workout.Entity.Member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApiClient is a Querydsl query type for ApiClient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApiClient extends EntityPathBase<ApiClient> {

    private static final long serialVersionUID = -1897776482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApiClient apiClient = new QApiClient("apiClient");

    public final app.workout.Entity.QBaseEntity _super = new app.workout.Entity.QBaseEntity(this);

    public final StringPath apiKey = createString("apiKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QApiClient(String variable) {
        this(ApiClient.class, forVariable(variable), INITS);
    }

    public QApiClient(Path<? extends ApiClient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApiClient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApiClient(PathMetadata metadata, PathInits inits) {
        this(ApiClient.class, metadata, inits);
    }

    public QApiClient(Class<? extends ApiClient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

