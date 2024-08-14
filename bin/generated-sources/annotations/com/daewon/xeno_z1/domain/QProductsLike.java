package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsLike is a Querydsl query type for ProductsLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsLike extends EntityPathBase<ProductsLike> {

    private static final long serialVersionUID = 972027468L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsLike productsLike = new QProductsLike("productsLike");

    public final NumberPath<Long> likeIndex = createNumber("likeIndex", Long.class);

    public final NumberPath<Long> productLikeId = createNumber("productLikeId", Long.class);

    public final QProductsColor productsColor;

    public QProductsLike(String variable) {
        this(ProductsLike.class, forVariable(variable), INITS);
    }

    public QProductsLike(Path<? extends ProductsLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsLike(PathMetadata metadata, PathInits inits) {
        this(ProductsLike.class, metadata, inits);
    }

    public QProductsLike(Class<? extends ProductsLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColor = inits.isInitialized("productsColor") ? new QProductsColor(forProperty("productsColor"), inits.get("productsColor")) : null;
    }

}

