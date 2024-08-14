package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsStar is a Querydsl query type for ProductsStar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsStar extends EntityPathBase<ProductsStar> {

    private static final long serialVersionUID = 972246279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsStar productsStar = new QProductsStar("productsStar");

    public final QProductsColor productsColor;

    public final NumberPath<Long> productStarId = createNumber("productStarId", Long.class);

    public final NumberPath<Double> starAvg = createNumber("starAvg", Double.class);

    public final NumberPath<Double> starTotal = createNumber("starTotal", Double.class);

    public QProductsStar(String variable) {
        this(ProductsStar.class, forVariable(variable), INITS);
    }

    public QProductsStar(Path<? extends ProductsStar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsStar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsStar(PathMetadata metadata, PathInits inits) {
        this(ProductsStar.class, metadata, inits);
    }

    public QProductsStar(Class<? extends ProductsStar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColor = inits.isInitialized("productsColor") ? new QProductsColor(forProperty("productsColor"), inits.get("productsColor")) : null;
    }

}

