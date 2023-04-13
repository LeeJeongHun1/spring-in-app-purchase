package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

//    Optional<Subscription> findByChannelId(Long channelId);
}