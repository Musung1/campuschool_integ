package com.example.campuschool_backend.domain.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegister is a Querydsl query type for Register
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegister extends EntityPathBase<Register> {

    private static final long serialVersionUID = -45626469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegister register = new QRegister("register");

    public final com.example.campuschool_backend.domain.auditing.QAuditingField _super = new com.example.campuschool_backend.domain.auditing.QAuditingField(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<com.example.campuschool_backend.domain.lecture.enums.RegisterStatus> status = createEnum("status", com.example.campuschool_backend.domain.lecture.enums.RegisterStatus.class);

    public final com.example.campuschool_backend.domain.user.QUserEntity user;

    public QRegister(String variable) {
        this(Register.class, forVariable(variable), INITS);
    }

    public QRegister(Path<? extends Register> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegister(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegister(PathMetadata metadata, PathInits inits) {
        this(Register.class, metadata, inits);
    }

    public QRegister(Class<? extends Register> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.campuschool_backend.domain.user.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

