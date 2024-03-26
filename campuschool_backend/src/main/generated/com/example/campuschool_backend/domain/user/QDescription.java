package com.example.campuschool_backend.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDescription is a Querydsl query type for Description
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDescription extends BeanPath<Description> {

    private static final long serialVersionUID = 1755610273L;

    public static final QDescription description1 = new QDescription("description1");

    public final StringPath description = createString("description");

    public final StringPath education = createString("education");

    public final StringPath history = createString("history");

    public QDescription(String variable) {
        super(Description.class, forVariable(variable));
    }

    public QDescription(Path<? extends Description> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDescription(PathMetadata metadata) {
        super(Description.class, metadata);
    }

}

