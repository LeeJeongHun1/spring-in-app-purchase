package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.repository.BaseRepositoryCustom;

import javax.persistence.EntityManager;

//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.Expression;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.springinapppurchase.dto.AppleInAppPurchaseDto;
//import com.springinapppurchase.dto.UserDto;
//import com.springinapppurchase.enumeration.PurchaseStatus;
//import com.springinapppurchase.repository.BaseRepositoryCustom;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.support.PageableExecutionUtils;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//import static com.springinapppurchase.entity.QAppleInAppPurchase.appleInAppPurchase;
//
//
public class AppleInAppPurchaseCustomRepositoryImpl extends BaseRepositoryCustom implements AppleInAppPurchaseCustomRepository {
    //
    public AppleInAppPurchaseCustomRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}
//
//    @Override
//    public List<AppleInAppPurchaseDto> findAllByTransactionIds(List<String> transactionIds) {
//        return queryFactory.select(buildExpressionForAppleIAP()
//                )
//                .from(appleInAppPurchase)
//                .where(appleInAppPurchase.transactionId.in(transactionIds))
//                .fetch();
//    }
//
//    @Override
//    public Page<AppleInAppPurchaseDto> findAll(Long userId, Long channelId) {
//        Pageable pageable = PageRequest.of(0, 10);
//        BooleanBuilder builder = new BooleanBuilder();
//        JPAQuery<AppleInAppPurchaseDto> query = queryFactory.select(buildExpressionForAppleIAP())
//                .from(appleInAppPurchase)
////                .innerJoin(appleInAppPurchase.channel)
//                .innerJoin(appleInAppPurchase.user);
//
////        builder.and(eqChannelId(channelId));
//        builder.and(eqUserId(userId));
//        query.where(builder);
//
//        // 정렬
//
//        JPAQuery<AppleInAppPurchaseDto> count = queryFactory.select(buildExpressionForAppleIAP())
//                .from(appleInAppPurchase)
//                .innerJoin(appleInAppPurchase.user)
//                .where(builder);
//
//        return PageableExecutionUtils.getPage(query.fetch(), pageable, count::fetchCount);
//    }
//
//    @Override
//    public Optional<AppleInAppPurchaseDto> findById(Long userId, Long channelId, Long id) {
//        return Optional.ofNullable(queryFactory.select(buildExpressionForAppleIAP())
//                .from(appleInAppPurchase)
////                .innerJoin(appleInAppPurchase.channel)
//                .innerJoin(appleInAppPurchase.user)
//                .where((eqUserId(userId))
//                        .and(appleInAppPurchase.id.eq(id)))
//                .fetchFirst());
//    }
//
//
//    @Override
//    public Optional<AppleInAppPurchaseDto> findByOriginalTransactionId(String originalTransactionId) {
//        return Optional.ofNullable(queryFactory.select(buildExpressionForAppleIAP())
//                .from(appleInAppPurchase)
////                .innerJoin(appleInAppPurchase.channel.user)
//                .where(appleInAppPurchase.originalTransactionId.eq(originalTransactionId)
//                        .and(appleInAppPurchase.status.eq(PurchaseStatus.COMPLETED)))
//                .fetchFirst());
//    }
//
//    @Override
//    public void updateStatus(AppleInAppPurchaseDto appleInAppPurchaseDto) {
//        queryFactory.update(appleInAppPurchase)
//                .set(appleInAppPurchase.status, appleInAppPurchaseDto.getStatus())
//                .where(appleInAppPurchase.id.eq(appleInAppPurchaseDto.getId()))
//                .execute();
//    }
//
//    private Expression<AppleInAppPurchaseDto> buildExpressionForAppleIAP() {
//        return Projections.fields(AppleInAppPurchaseDto.class,
//                appleInAppPurchase.id,
//                buildExpressionForUser(),
//                appleInAppPurchase.appItemId,
//                appleInAppPurchase.productId,
//                appleInAppPurchase.transactionId,
//                appleInAppPurchase.originalTransactionId,
//                appleInAppPurchase.status,
//                appleInAppPurchase.purchaseDate,
//                appleInAppPurchase.expirationDate
//        );
//    }
//    private Expression<UserDto> buildExpressionForUser() {
//        return Projections.fields(UserDto.class,
//                appleInAppPurchase.user.id,
//                appleInAppPurchase.user.userName
//        ).as("user");
//    }
//
//
//    private BooleanExpression eqUserId(Long userId) {
//        return userId == null ? null : appleInAppPurchase.user.id.eq(userId);
//    }
//
//    private BooleanExpression eqStatus(PurchaseStatus status) {
//        return status == null ? null : appleInAppPurchase.status.eq(status);
//    }
//}
