package com.springinapppurchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscriptionPlan is a Querydsl query type for SubscriptionPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscriptionPlan extends EntityPathBase<SubscriptionPlan> {

    private static final long serialVersionUID = -475759930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscriptionPlan subscriptionPlan = new QSubscriptionPlan("subscriptionPlan");

    public final DateTimePath<java.time.Instant> createDate = createDateTime("createDate", java.time.Instant.class);

    public final com.springinapppurchase.entity.user.QUser creator;

    public final StringPath duration = createString("duration");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxFollower = createNumber("maxFollower", Integer.class);

    public final NumberPath<Integer> minFollower = createNumber("minFollower", Integer.class);

    public final com.springinapppurchase.entity.user.QUser modifier;

    public final DateTimePath<java.time.Instant> modifyDate = createDateTime("modifyDate", java.time.Instant.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath productId = createString("productId");

    public final StringPath referenceName = createString("referenceName");

    public final StringPath type = createString("type");

    public QSubscriptionPlan(String variable) {
        this(SubscriptionPlan.class, forVariable(variable), INITS);
    }

    public QSubscriptionPlan(Path<? extends SubscriptionPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscriptionPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscriptionPlan(PathMetadata metadata, PathInits inits) {
        this(SubscriptionPlan.class, metadata, inits);
    }

    public QSubscriptionPlan(Class<? extends SubscriptionPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creator = inits.isInitialized("creator") ? new com.springinapppurchase.entity.user.QUser(forProperty("creator")) : null;
        this.modifier = inits.isInitialized("modifier") ? new com.springinapppurchase.entity.user.QUser(forProperty("modifier")) : null;
    }

}

