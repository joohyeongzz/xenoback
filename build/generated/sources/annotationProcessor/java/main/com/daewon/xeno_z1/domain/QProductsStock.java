package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductsStock is a Querydsl query type for ProductsStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductsStock extends EntityPathBase<ProductsStock> {

    private static final long serialVersionUID = 74876673L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductsStock productsStock = new QProductsStock("productsStock");

    public final QProductsColorSize productsColorSize;

    public final NumberPath<Long> productStockId = createNumber("productStockId", Long.class);

    public final NumberPath<Long> stock = createNumber("stock", Long.class);

    public QProductsStock(String variable) {
        this(ProductsStock.class, forVariable(variable), INITS);
    }

    public QProductsStock(Path<? extends ProductsStock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductsStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductsStock(PathMetadata metadata, PathInits inits) {
        this(ProductsStock.class, metadata, inits);
    }

    public QProductsStock(Class<? extends ProductsStock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productsColorSize = inits.isInitialized("productsColorSize") ? new QProductsColorSize(forProperty("productsColorSize"), inits.get("productsColorSize")) : null;
    }

}

