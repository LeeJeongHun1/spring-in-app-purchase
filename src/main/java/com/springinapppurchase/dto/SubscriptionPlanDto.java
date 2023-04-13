package com.springinapppurchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionPlanDto {
    private Long id;
    private int minFollower;
    private int maxFollower;
    private String productId;
    private BigDecimal price;
    private String duration;
    private String type;
}