package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsColorSize is a Querydsl query type for ProductsColorSize
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsColorSize extends EntityPathBase<ProductsColorSize> {

    private static final long serialVersionUID = 1921891439L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsColorSize productsColorSize = new QProductsColorSize("productsColorSize");

    public final NumberPath<Long> productColorSizeId = createNumber("productColorSizeId", Long.class);

    public final QProductsColor productsColor;

    public final EnumPath<Size> size = createEnum("size", Size.class);

    public QProductsColorSize(String variable) {
        this(ProductsColorSize.class, forVariable(variable), INITS);
    }

    public QProductsColorSize(Path<? extends ProductsColorSize> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsColorSize(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsColorSize(PathMetadata metadata, PathInits inits) {
        this(ProductsColorSize.class, metadata, inits);
    }

    public QProductsColorSize(Class<? extends ProductsColorSize> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColor = inits.isInitialized("productsColor") ? new QProductsColor(forProperty("productsColor"), inits.get("productsColor")) : null;
    }

}

