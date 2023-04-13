package com.springinapppurchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscription is a Querydsl query type for Subscription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscription extends EntityPathBase<Subscription> {

    private static final long serialVersionUID = 173654525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscription subscription = new QSubscription("subscription");

    public final DateTimePath<java.time.Instant> expirationDate = createDateTime("expirationDate", java.time.Instant.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isExpire = createBoolean("isExpire");

    public final DateTimePath<java.time.Instant> purchaseDate = createDateTime("purchaseDate", java.time.Instant.class);

    public final QSubscriptionPlan subscriptionPlan;

    public QSubscription(String variable) {
        this(Subscription.class, forVariable(variable), INITS);
    }

    public QSubscription(Path<? extends Subscription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscription(PathMetadata metadata, PathInits inits) {
        this(Subscription.class, metadata, inits);
    }

    public QSubscription(Class<? extends Subscription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subscriptionPlan = inits.isInitialized("subscriptionPlan") ? new QSubscriptionPlan(forProperty("subscriptionPlan"), inits.get("subscriptionPlan")) : null;
    }

}

