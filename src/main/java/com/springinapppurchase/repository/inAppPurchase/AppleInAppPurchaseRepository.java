package com.springinapppurchase.repository.inAppPurchase;

import com.springinapppurchase.entity.AppleInAppPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleInAppPurchaseRepository extends JpaRepository<AppleInAppPurchase, Long>, AppleInAppPurchaseCustomRepository {
}