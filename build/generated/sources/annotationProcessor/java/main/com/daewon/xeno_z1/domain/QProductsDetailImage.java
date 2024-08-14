package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsDetailImage is a Querydsl query type for ProductsDetailImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsDetailImage extends EntityPathBase<ProductsDetailImage> {

    private static final long serialVersionUID = 1353834293L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsDetailImage productsDetailImage = new QProductsDetailImage("productsDetailImage");

    public final StringPath fileName = createString("fileName");

    public final QProductsColor productsColor;

    public final NumberPath<Long> productsDetailImageId = createNumber("productsDetailImageId", Long.class);

    public final StringPath uuid = createString("uuid");

    public QProductsDetailImage(String variable) {
        this(ProductsDetailImage.class, forVariable(variable), INITS);
    }

    public QProductsDetailImage(Path<? extends ProductsDetailImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsDetailImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsDetailImage(PathMetadata metadata, PathInits inits) {
        this(ProductsDetailImage.class, metadata, inits);
    }

    public QProductsDetailImage(Class<? extends ProductsDetailImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColor = inits.isInitialized("productsColor") ? new QProductsColor(forProperty("productsColor"), inits.get("productsColor")) : null;
    }

}

