package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.entity.GoogleInAppPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleInAppPurchaseRepository extends JpaRepository<GoogleInAppPurchase, Long> {
    Boolean existsByOrderId(String orderId);

    Optional<GoogleInAppPurchase> findByPurchaseToken(String purchaseToken);
}