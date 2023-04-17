package com.springinapppurchase.entity;

import com.springinapppurchase.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "subscription")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "is_expire")
    private Boolean isExpire;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    public void updateSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public void updateExpire(Boolean expire) {
        isExpire = expire;
    }

    public void updatePurchaseDate(Instant purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void updateExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }
}
