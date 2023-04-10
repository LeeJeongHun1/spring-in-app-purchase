package com.springinapppurchase.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum PurchaseStatus {
    CANCELED,
    PAID,
    REFUNDED,
    COMPLETED
}
