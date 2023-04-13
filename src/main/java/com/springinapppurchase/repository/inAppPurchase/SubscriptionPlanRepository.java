package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {
    @Query("SELECT sp FROM SubscriptionPlan sp " +
            "WHERE (:followerCount BETWEEN sp.minFollower AND sp.maxFollower) " +
            "   OR (:followerCount < sp.minFollower AND sp.minFollower = (SELECT MIN(sp2.minFollower) FROM SubscriptionPlan sp2)) " +
            "   OR (:followerCount > sp.maxFollower AND sp.maxFollower = (SELECT MAX(sp3.maxFollower) FROM SubscriptionPlan sp3))")
    Optional<SubscriptionPlan> findByFollowerCount(@Param("followerCount") int followerCount);

    SubscriptionPlan findByProductId(String productId);


}
