package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsSeller is a Querydsl query type for ProductsSeller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsSeller extends EntityPathBase<ProductsSeller> {

    private static final long serialVersionUID = -1987724044L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsSeller productsSeller = new QProductsSeller("productsSeller");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProducts products;

    public final QUsers users;

    public QProductsSeller(String variable) {
        this(ProductsSeller.class, forVariable(variable), INITS);
    }

    public QProductsSeller(Path<? extends ProductsSeller> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsSeller(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsSeller(PathMetadata metadata, PathInits inits) {
        this(ProductsSeller.class, metadata, inits);
    }

    public QProductsSeller(Class<? extends ProductsSeller> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.products = inits.isInitialized("products") ? new QProducts(forProperty("products")) : null;
        this.users = inits.isInitialized("users") ? new QUsers(forProperty("users")) : null;
    }

}

