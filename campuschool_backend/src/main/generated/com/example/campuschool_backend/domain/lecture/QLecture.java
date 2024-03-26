package com.example.campuschool_backend.domain.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = -757796474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final com.example.campuschool_backend.domain.auditing.QAuditingField _super = new com.example.campuschool_backend.domain.auditing.QAuditingField(this);

    public final ListPath<AvaliableTime, QAvaliableTime> avaliableTimeList = this.<AvaliableTime, QAvaliableTime>createList("avaliableTimeList", AvaliableTime.class, QAvaliableTime.class, PathInits.DIRECT2);

    public final EnumPath<com.example.campuschool_backend.domain.lecture.enums.CategoryType> categoryType = createEnum("categoryType", com.example.campuschool_backend.domain.lecture.enums.CategoryType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final ListPath<CurriculumEntity, QCurriculumEntity> curriculumEntityList = this.<CurriculumEntity, QCurriculumEntity>createList("curriculumEntityList", CurriculumEntity.class, QCurriculumEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final StringPath description = createString("description");

    public final EnumPath<com.example.campuschool_backend.domain.lecture.enums.Difficulty> difficulty = createEnum("difficulty", com.example.campuschool_backend.domain.lecture.enums.Difficulty.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.example.campuschool_backend.domain.lecture.enums.LectureStatus> lectureStatus = createEnum("lectureStatus", com.example.campuschool_backend.domain.lecture.enums.LectureStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final ListPath<Notification, QNotification> notificationList = this.<Notification, QNotification>createList("notificationList", Notification.class, QNotification.class, PathInits.DIRECT2);

    public final StringPath refImage = createString("refImage");

    public final ListPath<Register, QRegister> registerList = this.<Register, QRegister>createList("registerList", Register.class, QRegister.class, PathInits.DIRECT2);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final com.example.campuschool_backend.domain.user.QUserEntity teacher;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.teacher = inits.isInitialized("teacher") ? new com.example.campuschool_backend.domain.user.QUserEntity(forProperty("teacher"), inits.get("teacher")) : null;
    }

}

