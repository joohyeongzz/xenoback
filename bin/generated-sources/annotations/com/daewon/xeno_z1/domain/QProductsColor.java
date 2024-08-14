package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsColor is a Querydsl query type for ProductsColor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsColor extends EntityPathBase<ProductsColor> {

    private static final long serialVersionUID = 59948878L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsColor productsColor = new QProductsColor("productsColor");

    public final StringPath color = createString("color");

    public final NumberPath<Long> productColorId = createNumber("productColorId", Long.class);

    public final QProducts products;

    public QProductsColor(String variable) {
        this(ProductsColor.class, forVariable(variable), INITS);
    }

    public QProductsColor(Path<? extends ProductsColor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsColor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsColor(PathMetadata metadata, PathInits inits) {
        this(ProductsColor.class, metadata, inits);
    }

    public QProductsColor(Class<? extends ProductsColor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.products = inits.isInitialized("products") ? new QProducts(forProperty("products")) : null;
    }

}

