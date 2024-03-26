package com.example.campuschool_backend.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1021007433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QDescription description;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath img = createString("img");

    public final EnumPath<LoginType> loginType = createEnum("loginType", LoginType.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath portfolioImg = createString("portfolioImg");

    public final ListPath<com.example.campuschool_backend.domain.lecture.Review, com.example.campuschool_backend.domain.lecture.QReview> reviewList = this.<com.example.campuschool_backend.domain.lecture.Review, com.example.campuschool_backend.domain.lecture.QReview>createList("reviewList", com.example.campuschool_backend.domain.lecture.Review.class, com.example.campuschool_backend.domain.lecture.QReview.class, PathInits.DIRECT2);

    public final EnumPath<RoleType> roleType = createEnum("roleType", RoleType.class);

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.description = inits.isInitialized("description") ? new QDescription(forProperty("description")) : null;
    }

}

