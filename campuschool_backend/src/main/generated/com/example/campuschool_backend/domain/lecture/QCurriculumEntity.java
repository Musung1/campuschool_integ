package com.example.campuschool_backend.domain.lecture;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCurriculumEntity is a Querydsl query type for CurriculumEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCurriculumEntity extends EntityPathBase<CurriculumEntity> {

    private static final long serialVersionUID = 1675180278L;

    public static final QCurriculumEntity curriculumEntity = new QCurriculumEntity("curriculumEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public QCurriculumEntity(String variable) {
        super(CurriculumEntity.class, forVariable(variable));
    }

    public QCurriculumEntity(Path<? extends CurriculumEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurriculumEntity(PathMetadata metadata) {
        super(CurriculumEntity.class, metadata);
    }

}

