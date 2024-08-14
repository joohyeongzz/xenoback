package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsImage is a Querydsl query type for ProductsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsImage extends EntityPathBase<ProductsImage> {

    private static final long serialVersionUID = 65419590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsImage productsImage = new QProductsImage("productsImage");

    public final StringPath fileName = createString("fileName");

    public final BooleanPath isMain = createBoolean("isMain");

    public final NumberPath<Long> productImageId = createNumber("productImageId", Long.class);

    public final QProductsColor productsColor;

    public final StringPath uuid = createString("uuid");

    public QProductsImage(String variable) {
        this(ProductsImage.class, forVariable(variable), INITS);
    }

    public QProductsImage(Path<? extends ProductsImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsImage(PathMetadata metadata, PathInits inits) {
        this(ProductsImage.class, metadata, inits);
    }

    public QProductsImage(Class<? extends ProductsImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColor = inits.isInitialized("productsColor") ? new QProductsColor(forProperty("productsColor"), inits.get("productsColor")) : null;
    }

}

