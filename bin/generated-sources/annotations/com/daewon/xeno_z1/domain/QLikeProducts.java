package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeProducts is a Querydsl query type for LikeProducts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeProducts extends EntityPathBase<LikeProducts> {

    private static final long serialVersionUID = -1360613044L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeProducts likeProducts = new QLikeProducts("likeProducts");

    public final BooleanPath isLike = createBoolean("isLike");

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final QProductsLike productsLike;

    public final QUsers users;

    public QLikeProducts(String variable) {
        this(LikeProducts.class, forVariable(variable), INITS);
    }

    public QLikeProducts(Path<? extends LikeProducts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeProducts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeProducts(PathMetadata metadata, PathInits inits) {
        this(LikeProducts.class, metadata, inits);
    }

    public QLikeProducts(Class<? extends LikeProducts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsLike = inits.isInitialized("productsLike") ? new QProductsLike(forProperty("productsLike"), inits.get("productsLike")) : null;
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

