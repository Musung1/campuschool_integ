package com.example.campuschool_backend.domain.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAvaliableTime is a Querydsl query type for AvaliableTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvaliableTime extends EntityPathBase<AvaliableTime> {

    private static final long serialVersionUID = -1889826408L;

    public static final QAvaliableTime avaliableTime = new QAvaliableTime("avaliableTime");

    public final EnumPath<com.example.campuschool_backend.domain.lecture.enums.Days> day = createEnum("day", com.example.campuschool_backend.domain.lecture.enums.Days.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> registerBy = createNumber("registerBy", Long.class);

    public final StringPath time = createString("time");

    public QAvaliableTime(String variable) {
        super(AvaliableTime.class, forVariable(variable));
    }

    public QAvaliableTime(Path<? extends AvaliableTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAvaliableTime(PathMetadata metadata) {
        super(AvaliableTime.class, metadata);
    }

}

