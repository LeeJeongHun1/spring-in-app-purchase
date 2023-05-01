package com.springinapppurchase.repository;

import com.springinapppurchase.repository.inAppPurchase.AppleInAppPurchaseRepository;
import com.springinapppurchase.repository.inAppPurchase.GoogleInAppPurchaseRepository;
import com.springinapppurchase.repository.inAppPurchase.SubscriptionPlanRepository;
import com.springinapppurchase.repository.inAppPurchase.SubscriptionRepository;
import com.springinapppurchase.repository.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class RepositoryWrapper {

    public final UserRepository user;
    public final SubscriptionPlanRepository subscriptionPlan;
    public final SubscriptionRepository subscription;
    public final AppleInAppPurchaseRepository appleInAppPurchase;
    public final GoogleInAppPurchaseRepository googleInAppPurchase;

}
