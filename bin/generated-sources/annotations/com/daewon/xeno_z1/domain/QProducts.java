package com.daewon.xeno_z1.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProducts is a Querydsl query type for Products
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducts extends EntityPathBase<Products> {

    private static final long serialVersionUID = 140403989L;

    public static final QProducts products = new QProducts("products");

    public final StringPath brandName = createString("brandName");

    public final StringPath category = createString("category");

    public final StringPath categorySub = createString("categorySub");

    public final BooleanPath isSale = createBoolean("isSale");

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> priceSale = createNumber("priceSale", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productNumber = createString("productNumber");

    public final StringPath season = createString("season");

    public QProducts(String variable) {
        super(Products.class, forVariable(variable));
    }

    public QProducts(Path<? extends Products> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProducts(PathMetadata metadata) {
        super(Products.class, metadata);
    }

}

