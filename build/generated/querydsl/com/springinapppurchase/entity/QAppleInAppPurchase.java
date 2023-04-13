package com.springinapppurchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppleInAppPurchase is a Querydsl query type for AppleInAppPurchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppleInAppPurchase extends EntityPathBase<AppleInAppPurchase> {

    private static final long serialVersionUID = -1118026877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppleInAppPurchase appleInAppPurchase = new QAppleInAppPurchase("appleInAppPurchase");

    public final NumberPath<Long> appItemId = createNumber("appItemId", Long.class);

    public final StringPath currency = createString("currency");

    public final DateTimePath<java.time.Instant> expirationDate = createDateTime("expirationDate", java.time.Instant.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalTransactionId = createString("originalTransactionId");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath productId = createString("productId");

    public final DateTimePath<java.time.Instant> purchaseDate = createDateTime("purchaseDate", java.time.Instant.class);

    public final StringPath receiptData = createString("receiptData");

    public final NumberPath<java.math.BigDecimal> refundPrice = createNumber("refundPrice", java.math.BigDecimal.class);

    public final DateTimePath<java.time.Instant> revocationDate = createDateTime("revocationDate", java.time.Instant.class);

    public final StringPath revocationReason = createString("revocationReason");

    public final EnumPath<com.springinapppurchase.enumeration.PurchaseStatus> status = createEnum("status", com.springinapppurchase.enumeration.PurchaseStatus.class);

    public final StringPath transactionId = createString("transactionId");

    public final com.springinapppurchase.entity.user.QUser user;

    public QAppleInAppPurchase(String variable) {
        this(AppleInAppPurchase.class, forVariable(variable), INITS);
    }

    public QAppleInAppPurchase(Path<? extends AppleInAppPurchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppleInAppPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppleInAppPurchase(PathMetadata metadata, PathInits inits) {
        this(AppleInAppPurchase.class, metadata, inits);
    }

    public QAppleInAppPurchase(Class<? extends AppleInAppPurchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.springinapppurchase.entity.user.QUser(forProperty("user")) : null;
    }

}

