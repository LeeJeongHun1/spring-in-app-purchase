package com.springinapppurchase.entity;

import com.springinapppurchase.entity.user.User;
import com.springinapppurchase.enumeration.PurchaseStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "apple_in_app_purchase")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AppleInAppPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "app_item_id")
    private Long appItemId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "original_transaction_id")
    private String originalTransactionId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    @Lob
    @Column(name = "receipt_data")
    private String receiptData;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private PurchaseStatus status;

    @Column(name = "revocation_reason")
    private String revocationReason;

    @Column(name = "refund_price")
    private BigDecimal refundPrice;

    @Column(name = "purchase_date")
    private Instant purchaseDate;

    @Column(name = "revocation_date")
    private Instant revocationDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    public void updateStatus(PurchaseStatus status) {
        this.status = status;
    }

    public void updateRevocationReason(String revocationReason) {
        this.revocationReason = revocationReason;
    }

    public void updateRevocationDate(Instant revocationDate) {
        this.revocationDate = revocationDate;
    }
}
